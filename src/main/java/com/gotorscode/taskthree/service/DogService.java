package com.gotorscode.taskthree.service;

import com.gotorscode.taskthree.dto.DogDto;
import com.gotorscode.taskthree.dto.UserDto;
import com.gotorscode.taskthree.model.DogEntity;
import com.gotorscode.taskthree.model.UserEntity;
import com.gotorscode.taskthree.repository.DogRepository;
import com.gotorscode.taskthree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service

public class DogService {

    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private UserRepository userRepository;

    public Long createDog(DogDto dogDto) throws Exception {

        if (!StringUtils.hasText(dogDto.getNickname())
            || userRepository.getById(dogDto.getOwnerId()).getIsDeleted()) {
            throw new Exception();
        }

        DogEntity dogEntity = new DogEntity();
        dogEntity.setNickname(dogDto.getNickname());
        dogEntity.setOwner(userRepository.getById(dogDto.getOwnerId()));
        dogRepository.save(dogEntity);

        return dogEntity.getId();
    }

    public DogDto getDog(Long dogId) throws Exception {

        DogEntity dogEntity = (dogRepository.getById(dogId).getIsDeleted()) ? null : dogRepository.getById(dogId);

        if (dogEntity == null) {
            throw new Exception();
        }

        DogDto dogDto = new DogDto();
        dogDto.setNickname(dogEntity.getNickname());
        dogDto.setOwnerId(dogEntity.getOwner().getId());

        return dogDto;
    }

    public List<DogDto> getAllDog() {

        List<DogDto> dogs = new ArrayList<>();
        for (long i = 1; i <= dogRepository.count(); i++) {

            DogDto dogDto = new DogDto();

            if (!dogRepository.getById(i).getIsDeleted()) {
                dogDto.setNickname(dogRepository.getById(i).getNickname());
                dogDto.setOwnerId(dogRepository.getById(i).getOwner().getId());
                dogs.add(dogDto);
            }
        }

        return dogs;
    }

    public void updateDog(Long dogId, DogDto dogDto) throws Exception {

        DogEntity dogEntity = dogRepository.getById(dogId);

        if ((!StringUtils.hasText(dogEntity.getNickname()))
            || (userRepository.getById(dogDto.getOwnerId()).getIsDeleted())) {
            throw new Exception();
        }

        dogEntity.setNickname(dogDto.getNickname());
        dogEntity.setOwner(userRepository.getById(dogDto.getOwnerId()));
        dogRepository.save(dogEntity);
    }

    public void deleteDog(Long dogId) /*throws Exception*/ {

        DogEntity dogEntity = dogRepository.getById(dogId);

        /*if (dogEntity == null) {
            throw new Exception();
        }*/

        dogEntity.setIsDeleted(true);
        dogRepository.save(dogEntity);
    }
}
