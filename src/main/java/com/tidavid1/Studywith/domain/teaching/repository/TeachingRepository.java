package com.tidavid1.Studywith.domain.teaching.repository;

import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {

    @Query(value = "select t from Teaching t where t.teachingId = :teachingId")
    Optional<Teaching> findByTeachingId(@Param("teachingId") Long teachingId);

    // select t from Teaching t where t.teachingId
    @Query(value = "select t from Teaching t where t.teacher = :teacherId and t.student = :studentId")
    Optional<Teaching> findByStudentId(@Param("teacherId") User teacherId, @Param("studentId") User studentId);

    @Query(value = "select t from Teaching t where t.teacher = :teacherId")
    List<Teaching> findByTeacherId(@Param("teacherId") User teacherId);

}
