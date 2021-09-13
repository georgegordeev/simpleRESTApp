package com.gotorscode.taskthree.service;

import com.gotorscode.taskthree.dto.UserDto;
import com.gotorscode.taskthree.model.UserEntity;
import com.gotorscode.taskthree.repository.DogRepository;
import com.gotorscode.taskthree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DogRepository dogRepository;

    public Long createUser(UserDto userDto) throws Exception {

        if (!StringUtils.hasText(userDto.getFirstName())
            || !StringUtils.hasText(userDto.getLastName())) {
            throw new Exception();
        }

        UserEntity userEntity = new UserEntity(userDto);
        userRepository.save(userEntity);

        return userEntity.getId();
    }

    public UserDto getUser(Long userId) throws Exception {

        UserEntity userEntity = (userRepository.getById(userId).getIsDeleted()) ? null : userRepository.getById(userId);

        if (userEntity == null) {
            throw new Exception();
        }

        return new UserDto(userEntity);
    }

    public List<UserDto> getAllUser() {

        List<UserDto> users = new ArrayList<>();
        for (long i = 1; i <= userRepository.count(); i++) {
            if (!userRepository.getById(i).getIsDeleted()) {
                users.add(new UserDto(userRepository.getById(i)));
            }
        }

        return users;
    }

    public List<String> getAllDogsForUser(Long userId) {

        List<String> dogs = new ArrayList<>();

        for (long i = 1; i <= dogRepository.count(); i++) {

            if (!dogRepository.getById(i).getIsDeleted()
                && !userRepository.getById(userId).getIsDeleted()
                && userId.equals(dogRepository.getById(i).getOwner().getId())) {

                dogs.add(dogRepository.getById(i).getNickname());
            }
        }

        return dogs;
    }

    public void updateUser(Long userId, UserDto userDto) throws Exception {

        UserEntity userEntity = userRepository.getById(userId);

        if (!StringUtils.hasText(userDto.getFirstName())
            || !StringUtils.hasText(userDto.getLastName())) {
            throw new Exception();
        }

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userRepository.save(userEntity);
    }

    public void deleteUser(Long userId) {

        UserEntity userEntity = userRepository.getById(userId);
        userEntity.setIsDeleted(true);
        userRepository.save(userEntity);
    }
}
