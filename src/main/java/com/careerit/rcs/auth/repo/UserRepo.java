package com.careerit.rcs.auth.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.careerit.rcs.auth.domain.User;

public interface UserRepo extends MongoRepository<User, String>{

}
