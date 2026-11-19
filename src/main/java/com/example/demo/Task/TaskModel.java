package com.example.demo.Task;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //impede que a tarefa criada seja associada ao usuário//
    @Column(nullable = false)
    private UUID idUser;

    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
