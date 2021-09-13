package com.gotorscode.taskthree.model;

import com.gotorscode.taskthree.dto.DogDto;
import com.gotorscode.taskthree.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor

public class DogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private Boolean isDeleted = false;

    @ManyToOne
    private UserEntity owner;

}
