package com.irs.register.register.infra.db.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.irs.register.register.entity.User;

@Repository
public interface AuthRepository extends CrudRepository<User, String>{

}
