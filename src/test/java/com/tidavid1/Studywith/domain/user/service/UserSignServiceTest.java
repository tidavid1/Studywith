package com.tidavid1.Studywith.domain.user.service;

import com.tidavid1.Studywith.domain.user.dto.UserLoginRequestDto;
import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CIdLoginFailedException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserSignServiceTest {
    @InjectMocks
    private UserSignService userSignService;
    @Mock
    private UserRepository userRepository;

    @DisplayName("Test signup Success")
    @Test
    void testSignupSuccess() {
        // Arrange
        UserSignupRequestDto userSignupRequestDto = new UserSignupRequestDto("test", "test!", "홍길동", "010-0000-0000");
        User expectedUser = userSignupRequestDto.toEntity();
        Long expectedUserId = 1L;
        ReflectionTestUtils.setField(expectedUser, "userId", expectedUserId);

        given(userRepository.save(any(User.class))).willReturn(expectedUser);
        given(userRepository.findByUserId(expectedUserId)).willReturn(Optional.of(expectedUser));

        // Act
        Long actualUserId = userSignService.signup(userSignupRequestDto);
        User actualUser = userRepository.findByUserId(actualUserId).orElse(null);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUserId, actualUserId);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getPhoneCall(), actualUser.getPhoneCall());
    }


    @DisplayName("Test Login Failed")
    @Test
    void testLoginFail() {
        // Arrange
        String id = "test";
        String passwd = "test!";
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(id, passwd);
        // Act & Assert
        assertThrows(CIdLoginFailedException.class, ()-> userSignService.login(userLoginRequestDto));
    }
}