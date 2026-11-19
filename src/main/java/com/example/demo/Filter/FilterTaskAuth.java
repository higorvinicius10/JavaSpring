

package com.example.demo.Filter;

import com.example.demo.User.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Metodo que ignora as seguintes requisições HTTP, fazendo elas não passarem pelo filtro//
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.equals("/users/")
                || path.equals("/users")
                || path.startsWith("/h2-console");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Pegar o cabeçalho Authorization
        var authorization = request.getHeader("Authorization");

        // 2. Verificar se existe autenticação Basic
        if (authorization == null ||
                !authorization.startsWith("Basic ")) {

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Autenticacao necessaria"
            );
            return;
        }

        try {
            // 3. Remover o prefixo Basic
            var authEncoder = authorization
                    .substring("Basic ".length())
                    .trim();

            // 4. Decodificar Base64
            byte[] authDecode = Base64.getDecoder()
                    .decode(authEncoder);

            var authString = new String(
                    authDecode,
                    java.nio.charset.StandardCharsets.UTF_8
            );

            // 5. Separar usuário e senha
            String[] credentials = authString.split(":", 2);

            if (credentials.length != 2) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String username = credentials[0];
            String password = credentials[1];

            // 6. Buscar usuário no banco
            var user = userRepository.findByUserName(username);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // 7. Verificar senha usando BCrypt
            boolean passwordVerify = passwordEncoder.matches(
                    password,
                    user.getPassword()
            );

            if (!passwordVerify) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // 8. Autenticação aprovada
            filterChain.doFilter(request, response);

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
