package com.gotorscode.taskthree.dto;

import com.gotorscode.taskthree.model.DogEntity;
import com.gotorscode.taskthree.model.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class DogDto {

    private String nickname;

    private Long ownerId;

    DogDto (DogEntity dogEntity) {
        this.nickname = dogEntity.getNickname();
        this.ownerId = dogEntity.getOwner().getId();
    }
}
