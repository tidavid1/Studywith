package com.tidavid1.Studywith.domain.studylog.service;

import com.tidavid1.Studywith.domain.studylog.dto.StudyLogRequestDto;
import com.tidavid1.Studywith.domain.studylog.entity.StudyLog;
import com.tidavid1.Studywith.domain.studylog.exception.CStudyLogAlreadyExistException;
import com.tidavid1.Studywith.domain.studylog.repository.StudyLogRepository;
import com.tidavid1.Studywith.domain.teaching.dto.TeachingRequestDto;
import com.tidavid1.Studywith.domain.teaching.entity.Language;
import com.tidavid1.Studywith.domain.teaching.repository.TeachingRepository;
import com.tidavid1.Studywith.domain.teaching.service.TeachingService;
import com.tidavid1.Studywith.domain.user.dto.UserLoginRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import com.tidavid1.Studywith.domain.user.service.UserSignService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudyLogServiceTest {

    @Autowired
    private StudyLogService studyLogService;
    @Autowired
    private TeachingService teachingService;
    @Autowired
    private UserSignService userSignService;
    @Autowired
    private StudyLogRepository studyLogRepository;
    @Autowired
    private TeachingRepository teachingRepository;
    @Autowired
    private UserRepository userRepository;

    private Long teachingId;
    private StudyLogRequestDto studyLogRequestDto;
    private String accessToken;
    @BeforeEach
    void setup(){
        Long teacherId = userSignService.signup(UserSignupRequestDto.builder()
                .id("teacher")
                .passwd("teacher!")
                .name("선생님")
                .phoneCall("010-1234-5678")
                .role(Role.ROLE_Teacher)
                .build());
        Long studentId = userSignService.signup(UserSignupRequestDto.builder()
                .id("student")
                .passwd("student!")
                .name("학생")
                .phoneCall("student")
                .role(Role.ROLE_Student)
                .build());
        accessToken = userSignService.login(
                UserLoginRequestDto.builder()
                        .id("teacher")
                        .passwd("teacher!")
                        .build()
        ).getAccessToken();
        teachingId = teachingService.createClass(TeachingRequestDto.builder()
                .teacherId(teacherId)
                .studentId(studentId)
                .startDate(LocalDate.now())
                .language(Language.Python)
                .build());
        studyLogRequestDto = new StudyLogRequestDto(teachingId, "", LocalDate.now());
    }

    @AfterEach
    void cleanup(){
        studyLogRepository.deleteAll();
        teachingRepository.deleteAll();
        userRepository.deleteAll();
    }

    @DisplayName("Test createStudyLog Success")
    @Test
    void testCreateStudyLogSuccess(){
        // Act
        Long studyLogId = studyLogService.createStudyLog(studyLogRequestDto);
        StudyLog studyLog = studyLogRepository.findByStudyLogId(studyLogId).get();
        // Assert
        assertNotNull(studyLog);
        assertEquals(studyLogId, studyLog.getStudyLogId());
    }

    @DisplayName("Test createStudyLog Failed")
    @Test
    void testCreateStudyLogFailed(){
        // Arrange
        studyLogService.createStudyLog(studyLogRequestDto);
        // Act & Assert
        assertThrows(CStudyLogAlreadyExistException.class, ()-> studyLogService.createStudyLog(studyLogRequestDto));
    }

    @DisplayName("Test updateStudyNote Success")
    @Test
    void testUpdateStudyNoteSuccess(){
        // Arrange
        String expectedStudyNote = "Test";
        Long expectedStudyLogId = studyLogService.createStudyLog(studyLogRequestDto);
        // Act
        Long actualStudyLogId = studyLogService.updateStudyNote(expectedStudyLogId, StudyLogRequestDto.builder().studyNote(expectedStudyNote).build());
        StudyLog actualStudyLog = studyLogRepository.findByStudyLogId(actualStudyLogId).get();
        // Assert
        assertNotNull(actualStudyLog);
        assertEquals(expectedStudyLogId, actualStudyLogId);
        assertEquals(expectedStudyNote, actualStudyLog.getStudyNote());
    }

}