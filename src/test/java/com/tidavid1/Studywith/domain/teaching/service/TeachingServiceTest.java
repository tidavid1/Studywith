package com.tidavid1.Studywith.domain.teaching.service;

import com.tidavid1.Studywith.domain.teaching.dto.TeachingRequestDto;
import com.tidavid1.Studywith.domain.teaching.entity.Language;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingAlreadyExistException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingEndDateEarlierThenStartDateException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingNotFoundException;
import com.tidavid1.Studywith.domain.teaching.repository.TeachingRepository;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
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
class TeachingServiceTest {
    @Autowired
    private TeachingService teachingService;
    @Autowired
    private TeachingRepository teachingRepository;
    @Autowired
    private UserSignService userSignService;
    @Autowired
    private UserRepository userRepository;

    private Long teacherUserId;
    private Long studentUserId;
    private TeachingRequestDto teachingRequestDto;
    @BeforeEach
    void setup(){
        UserSignupRequestDto teacherSignupRequestDto = UserSignupRequestDto.builder()
                .id("teacher")
                .passwd("teacher!")
                .name("선생님")
                .phoneCall("010-1234-5678")
                .build();
        UserSignupRequestDto studentSignupRequestDto = UserSignupRequestDto.builder()
                .id("student")
                .passwd("student!")
                .name("학생")
                .phoneCall("010-1111-1111")
                .build();
        teacherUserId = userSignService.signup(teacherSignupRequestDto);
        studentUserId = userSignService.signup(studentSignupRequestDto);
        teachingRequestDto = TeachingRequestDto.builder()
                .teacherId(teacherUserId)
                .studentId(studentUserId)
                .startDate(LocalDate.now())
                .language(Language.Java)
                .build();
    }

    @AfterEach
    void cleanup(){
        teachingRepository.deleteAll();
        userRepository.deleteAll();
    }

    @DisplayName("Test makeClass Success")
    @Test
    void testMakeClassSuccess(){
        // Arrange
        User teacher = userRepository.findByUserId(teacherUserId).orElseThrow(CUserNotFoundException::new);
        User student = userRepository.findByUserId(studentUserId).orElseThrow(CUserNotFoundException::new);
        Teaching expectedTeaching = teachingRequestDto.toEntity(teacher, student);
        // Act
        Long expectedTeachingId = teachingService.makeClass(teachingRequestDto);
        Teaching actualTeaching = teachingRepository.findByTeachingId(expectedTeachingId).orElseThrow(CTeachingNotFoundException::new);
        // Assert
        assertNotNull(actualTeaching);
        assertEquals(expectedTeachingId, actualTeaching.getTeachingId());
        assertEquals(expectedTeaching.getTeacher().getId(), actualTeaching.getTeacher().getId());
        assertEquals(expectedTeaching.getStudent().getId(), actualTeaching.getStudent().getId());
        assertEquals(expectedTeaching.getStartDate(), actualTeaching.getStartDate());
        assertEquals(expectedTeaching.getLanguage(), actualTeaching.getLanguage());
    }

    @DisplayName("Test makeClass Failed")
    @Test
    void testMakeClassFailed(){
        // Arrange
        teachingService.makeClass(teachingRequestDto);
        // Act & Assert
        assertThrows(CTeachingAlreadyExistException.class, ()-> teachingService.makeClass(teachingRequestDto));
    }

    @DisplayName("Test updateEndDate Success")
    @Test
    void testUpdateEndDateSuccess(){
        // Arrange
        Long expectedTeachingId = teachingService.makeClass(teachingRequestDto);
        LocalDate expectedEndDate = LocalDate.now().plusDays(1);
        // Act
        Long actualTeachingId = teachingService.updateEndDate(expectedTeachingId, TeachingRequestDto.builder().endDate(expectedEndDate).build());
        Teaching actualTeaching = teachingRepository.findByTeachingId(actualTeachingId).orElseThrow(CTeachingNotFoundException::new);
        // Assert
        assertNotNull(actualTeaching);
        assertEquals(expectedTeachingId, actualTeachingId);
        assertEquals(expectedEndDate, actualTeaching.getEndDate());
    }

    @DisplayName("Test updateEndDate Failed")
    @Test
    void testUpdateEndDateFailed(){
        // Arrange
        Long expectedTeachingId = teachingService.makeClass(teachingRequestDto);
        LocalDate expectedEndDate = LocalDate.now().minusDays(1);
        // Act & Assert
        assertThrows(CTeachingEndDateEarlierThenStartDateException.class, ()->teachingService.updateEndDate(expectedTeachingId, TeachingRequestDto.builder().endDate(expectedEndDate).build()));
    }

}