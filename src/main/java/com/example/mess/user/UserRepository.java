package com.example.mess.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByRollNo(String rollNo);

    void deleteByRollNo(String rollNo);

    // Uncomment if needed
    // UserEntity findByRollNumber(String RollNumber);
}

