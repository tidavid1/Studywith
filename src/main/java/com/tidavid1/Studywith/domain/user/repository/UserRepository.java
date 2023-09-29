package com.tidavid1.Studywith.domain.user.repository;

import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.userId = :userId and u.isActive = true")
    Optional<User> findByUserId(@Param("userId") Long userId);

    @Query("select u from User u where u.id = :id and u.isActive = true")
    Optional<User> findById(@Param("id") String id);

    @Query("select u from User u where u.role = :role")
    List<User> findByRole(@Param("role") Role role);

    @Query("select u from User u where u.userId = :userId and u.role = :role")
    Optional<User> findByUserIdWithRole(@Param("userId") Long userId, @Param("role") Role role);
}
