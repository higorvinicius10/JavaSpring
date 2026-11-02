
package com.example.demo.User.controller;

import com.example.demo.User.DTO.UserResponseDTO;
import com.example.demo.User.repository.IUserRepository;
import com.example.demo.User.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class MyController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    //ResponseEntity: permite devolver uma resposta HTTP personalizada.
    //@RequestBody: recebe as informações enviadas na requisição.
    //UserModel userModel representa os dados do usuário.
    @PostMapping("/")
   public ResponseEntity create(@RequestBody UserModel userModel){
        //userRepository: acessa o repositório.
        //findByUsername(): procura um usuário pelo nome de usuário.
        //userModel.getUserName(): pega o username que chegou na requisição.
        var user = this.userRepository.findByUserName(userModel.getUserName());

        if(user != null) {
            System.out.println("Usúario Já existe");
            //ResponseEntity.status(): define o código da resposta HTTP.
            //.body(): envia uma mensagem no corpo da resposta.
            //HttpStatus.BAD_REQUEST: representa o erro HTTP 400.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        // Criptografa a senha antes de salvar
        userModel.setPassword(
                passwordEncoder.encode(userModel.getPassword())
        );

            //userRepository.save(): salva o usuário no banco de dados.
            //userModel: contém as informações recebidas.
            //userCreated: guarda o usuário que foi salvo.
            //body(userCreated): devolve o usuário cadastrado na resposta.
        var userCreated = this.userRepository.save(userModel);

        //cria uma resposta sem a senha
        var response = new UserResponseDTO(
                userCreated.getId(),
                userCreated.getName(),
                userCreated.getUserName()
        );


        return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }
    }

