
package com.example.demo.User.controller;

// DTO de resposta: não expõe a senha do usuário.
import com.example.demo.User.DTO.UserResponseDTO;

// Repositório e entidade.
import com.example.demo.User.repository.IUserRepository;
import com.example.demo.User.Model.UserModel;

// Spring.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MyController {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    // Injeção de dependências pelo construtor.
    public MyController(
            PasswordEncoder passwordEncoder,
            IUserRepository userRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    // Endpoint responsável pelo cadastro.
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {

        // 1. Verifica se o nome de usuário já está cadastrado.
        var user = userRepository.findByUserName(
                userModel.getUserName()
        );

        if (user != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuário já existe.");
        }

        // 2. Criptografa a senha antes de salvar no banco.
        userModel.setPassword(
                passwordEncoder.encode(userModel.getPassword())
        );

        // 3. Salva o usuário no banco de dados.
        var userCreated = userRepository.save(userModel);

        // 4. Cria uma resposta sem expor a senha.
        var response = new UserResponseDTO(
                userCreated.getId(),
                userCreated.getName(),
                userCreated.getUserName()
        );

        // 5. Retorna HTTP 201 (Created).
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
