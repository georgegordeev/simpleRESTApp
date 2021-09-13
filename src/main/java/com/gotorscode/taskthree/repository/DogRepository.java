package com.gotorscode.taskthree.repository;

import com.gotorscode.taskthree.model.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<DogEntity, Long> {

}
