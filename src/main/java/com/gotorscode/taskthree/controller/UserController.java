package com.gotorscode.taskthree.controller;

import com.gotorscode.taskthree.dto.UserDto;
import com.gotorscode.taskthree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/user")
    public Long createUser(@RequestBody UserDto userDto) throws Exception {

        return userService.createUser(userDto);
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable(name = "id") Long id) throws  Exception {

        return userService.getUser(id);
    }

    @GetMapping("/user/all")
    public List<UserDto> getAllUsers() {

        return userService.getAllUser();
    }

    @GetMapping("/user/{id}/dogs")
    public List<String> getAllDogsForUser(@PathVariable("id") Long userId) {

        return userService.getAllDogsForUser(userId);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {

        userService.updateUser(id, userDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
