package com.ethan.stage.dal;

// import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDO, Long> {}
