package com.testproject.registration.repo;

import com.testproject.registration.repo.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
