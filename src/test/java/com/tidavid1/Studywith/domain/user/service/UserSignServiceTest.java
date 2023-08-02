package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserLoginRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CIdLoginFailedException;
import com.tidavid1.Studywith.domain.user.exception.CIdSignupFailedException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserSignServiceTest {
    @Autowired
    private UserSignService userSignService;
    @Autowired
    private UserRepository userRepository;

    private UserSignupRequestDto userSignupRequestDto;

    @BeforeEach
    void setup(){
        userSignupRequestDto = new UserSignupRequestDto("test", "test!", "홍길동", "010-0000-0000");
    }
    @AfterEach
    void cleanup(){
        userRepository.deleteAll();
    }

    @DisplayName("Test signup Success")
    @Test
    void testSignupSuccess() {
        // Arrange
        User expectedUser = userSignupRequestDto.toEntity();

        // Act
        Long actualUserId = userSignService.signup(userSignupRequestDto);
        User actualUser = userRepository.findByUserId(actualUserId).orElse(null);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getPhoneCall(), actualUser.getPhoneCall());
    }

    @DisplayName("Test signup Failed")
    @Test
    void testSignupFailed(){
        // Act
        userSignService.signup(userSignupRequestDto);
        // Assert
        assertThrows(CIdSignupFailedException.class, ()-> userSignService.signup(userSignupRequestDto));
    }

    @DisplayName("Test Login Success")
    @Test
    void testLoginSuccess(){
        // Arrange
        Long expectedUserId = userSignService.signup(userSignupRequestDto);
        // Act
        Long actualUserId = userSignService.login(new UserLoginRequestDto("test", "test!"));
        // Assert
        assertEquals(expectedUserId, actualUserId);
    }

    @DisplayName("Test Login Failed")
    @Test
    void testLoginFail() {
        // Arrange
        userSignService.signup(userSignupRequestDto);
        // Act & Assert
        assertThrows(CIdLoginFailedException.class, ()-> userSignService.login(new UserLoginRequestDto("temp", "temp")));
        assertThrows(CIdLoginFailedException.class, ()->userSignService.login(new UserLoginRequestDto("test", "test!!")));
    }
}