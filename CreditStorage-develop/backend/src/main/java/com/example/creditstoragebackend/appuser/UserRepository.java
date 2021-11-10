package com.example.creditstoragebackend.appuser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends MongoRepository<User, String> {

    Optional<User> findByUsername(@Param("username") String username);

    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableAppUser(String username);



}
