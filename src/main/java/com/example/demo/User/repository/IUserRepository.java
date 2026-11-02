package com.example.demo.User.repository;

import com.example.demo.User.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByUserName(String userName);

}