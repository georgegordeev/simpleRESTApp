package com.gotorscode.taskthree.dto;

import com.gotorscode.taskthree.model.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserDto {

    private String firstName;

    private String lastName;

    public UserDto(UserEntity userEntity) {
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
    }

}
