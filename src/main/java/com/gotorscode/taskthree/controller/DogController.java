package com.gotorscode.taskthree.controller;

import com.gotorscode.taskthree.dto.DogDto;
import com.gotorscode.taskthree.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    DogService dogService;

    @PostMapping("/dog")
    public Long createDog(@RequestBody DogDto dogDto) throws Exception {

        return dogService.createDog(dogDto);
    }

    @GetMapping("/dog/{id}")
    public DogDto getDog(@PathVariable("id") Long dogId) throws Exception {

        return dogService.getDog(dogId);
    }

    @GetMapping("/dog/all")
    public List<DogDto> getAllDog() {

        return dogService.getAllDog();
    }

    @PutMapping("/dog/{id}")
    public ResponseEntity<?> updateDog(@PathVariable("id") Long dogId, @RequestBody DogDto dogDto) throws Exception {

        dogService.updateDog(dogId, dogDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dog/{id}")
    public ResponseEntity<?> deleteDog(@PathVariable("id") Long dogId) {

        dogService.deleteDog(dogId);

        return ResponseEntity.ok().build();
    }
}
