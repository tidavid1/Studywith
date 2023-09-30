package com.tidavid1.Studywith.domain.studylog.repository;

import com.tidavid1.Studywith.domain.studylog.entity.StudyLog;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudyLogRepository extends JpaRepository<StudyLog, Long> {
    @Query("select s from StudyLog s where s.studyLogId = :studyLogId")
    Optional<StudyLog> findByStudyLogId(@Param("studyLogId") Long studyLogId);

    @Query("select s from StudyLog s where s.teaching = :teaching")
    List<StudyLog> findAllStudyLogWithTeaching(@Param("teaching") Teaching teaching);

    @Query("select s from StudyLog s where s.teaching = :teaching and s.studyDate = :studyDate")
    Optional<StudyLog> findByTeachingWithStudyDate(@Param("teaching") Teaching teaching, @Param("studyDate") LocalDate studyDate);
}
