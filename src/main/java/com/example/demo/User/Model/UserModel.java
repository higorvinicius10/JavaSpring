package com.example.demo.User.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

//usar gett and sett sem declaração
@Data
//nome da tabela
@Entity(name = "tb_users")
public class UserModel {

    //definir e gerar id para o user
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;


    //para não validar repetições
    @Column(unique = true)
    String name;
    private String userName;
    private String password;

    //informa a data que tal informação foi adicionada ao banco de dados
    @CreationTimestamp
    private LocalDate createdAt;

}


