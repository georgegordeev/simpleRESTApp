package com.gotorscode.taskthree.model;

import com.gotorscode.taskthree.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Boolean isDeleted = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<DogEntity> dogs;

    public UserEntity(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
    }
}
