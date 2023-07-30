package com.tidavid1.Studywith.domain.user.repository;

import com.tidavid1.Studywith.domain.user.dto.UserSignupRequestDto;
import com.tidavid1.Studywith.domain.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        UserSignupRequestDto userSignupRequestDto = new UserSignupRequestDto("test", "test!", "홍길동", "010-1111-1111");
        userRepository.save(userSignupRequestDto.toEntity());
        userSignupRequestDto = new UserSignupRequestDto("test1", "test!", "가나다", "010-1234-5678");
        userRepository.save(userSignupRequestDto.toEntity());
    }

    @AfterEach
    void cleanup(){
        userRepository.deleteAll();
    }

    @DisplayName("Test findByUserId Success")
    @Test
    void testFindByUserId_whenTrueValueInput_shouldReturnUser() {
        // Arrange
        Long expectedUserId = 1L;
        String expectedId = "test";
        String expectedName = "홍길동";
        String expectedPhoneCall = "010-1111-1111";
        // Act
        User user = userRepository.findById(expectedUserId).orElse(null);
        // Assert
        assertNotNull(user);
        assertEquals(expectedUserId, user.getUserId());
        assertEquals(expectedId, user.getId());
        assertEquals(expectedName, user.getName());
        assertEquals(expectedPhoneCall, user.getPhoneCall());
    }

    @DisplayName("Test findByUserId Failed")
    @Test
    void testFindByUserID_whenWrongUserIdInput_shouldReturnOptionalNull(){
        // Arrange
        Long userId = 5L;
        // Act
        User user = userRepository.findByUserId(userId).orElse(null);
        // Assert
        assertNull(user);
    }
    
    @DisplayName("Test findById Success")
    @Test
    void testFindById_whenTrueValueInput_shouldReturnUser() {
        // Arrange
        String expectedId = "test1";
        String expectedName = "가나다";
        String expectedPhoneCall = "010-1234-5678";
        // Act
        User user = userRepository.findById(expectedId).orElse(null);
        // Assert
        assertNotNull(user);
        assertEquals(expectedId, user.getId());
        assertEquals(expectedName, user.getName());
        assertEquals(expectedPhoneCall, user.getPhoneCall());
    }

    @DisplayName("Test findById Failed")
    @Test
    void testFindById_whenWrongIdInput_shouldReturnOptionalNull(){
        // Arrange
        String id = "라마바";
        // Act
        User user = userRepository.findById(id).orElse(null);
        // Assert
        assertNull(user);
    }
}