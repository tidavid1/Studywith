package com.tidavid1.Studywith.domain.user.repository;

import com.tidavid1.Studywith.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.userId = :userId and u.isActive = true")
    Optional<User> findByUserId(@Param("userId") Long userId);

    @Query("select u from User u where u.id = :id and u.isActive = true")
    Optional<User> findById(@Param("id") String id);
}
