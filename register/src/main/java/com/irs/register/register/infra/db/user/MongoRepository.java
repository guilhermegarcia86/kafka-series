package com.irs.register.register.infra.db.user;

import org.springframework.stereotype.Repository;

import com.irs.register.register.entity.User;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<User, String>{

}
