package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSignService userSignService;

    @Autowired
    private UserRepository userRepository;

    private Long expectedUserId;
    private User expectedUser;

    @BeforeEach
    void setup(){
        UserSignupRequestDto userSignupRequestDto = new UserSignupRequestDto("test", "test!", "홍길동", "010-1111-1111");
        expectedUser = userSignupRequestDto.toEntity();
        expectedUserId = userSignService.signup(userSignupRequestDto);
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }


    @DisplayName("Test findByUserId Success")
    @Test
    void testFindByUserIdSuccess() {
        // Act
        UserResponseDto actualUserResponseDto = userService.findByUserId(expectedUserId);
        // Assert
        assertNotNull(actualUserResponseDto);
        assertEquals(expectedUserId, actualUserResponseDto.getUserId());
        assertEquals(expectedUser.getId(), actualUserResponseDto.getId());
        assertEquals(expectedUser.getName(), actualUserResponseDto.getName());
        assertEquals(expectedUser.getPhoneCall(), actualUserResponseDto.getPhoneCall());
    }

    @DisplayName("Test findByUserId Failed")
    @Test
    void testFindByUserIdFailed(){
        // Act & Assert
        assertThrows(CUserNotFoundException.class, ()->userService.findByUserId(Long.MAX_VALUE));
    }

    @DisplayName("test findById Success")
    @Test
    void testFindByIdSuccess(){
        // Act
        UserResponseDto actualUserResponseDto = userService.findById(expectedUser.getId());
        // Assert
        assertNotNull(actualUserResponseDto);
        assertEquals(expectedUserId, actualUserResponseDto.getUserId());
        assertEquals(expectedUser.getId(), actualUserResponseDto.getId());
        assertEquals(expectedUser.getName(), actualUserResponseDto.getName());
        assertEquals(expectedUser.getPhoneCall(), actualUserResponseDto.getPhoneCall());
    }

    @DisplayName("test findById Failed")
    @Test
    void testFindByIdFailed(){
        // Act & Assert
        assertThrows(CUserNotFoundException.class, ()-> userService.findById("test1"));
    }

    @DisplayName("Test findAllUser")
    @Test
    void findAllUser() {
        // Act & Assert
        List<UserResponseDto> userResponseDtoList = userService.findAllUser();
        assertNotNull(userResponseDtoList);
        assertEquals(1, userResponseDtoList.size());
    }

    @DisplayName("Test updatePhoneCall")
    @Test
    void testUpdatePhoneCall(){
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto("test", "홍길동","010-1234-5678");
        // Act
        Long actualUserId = userService.updatePhoneCall(expectedUserId, userRequestDto);
        UserResponseDto actualUserResponseDto = userService.findByUserId(actualUserId);
        // Assert
        assertEquals(expectedUserId, actualUserId);
        assertEquals(userRequestDto.getPhoneCall(), actualUserResponseDto.getPhoneCall());
    }
}