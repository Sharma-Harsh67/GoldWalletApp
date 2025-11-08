package com.wallet.app.serviceimpl;

import com.wallet.app.model.Users;
import com.wallet.app.repository.UsersRepositoryJoshika;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplJoshikaTest {

    @Mock
    private UsersRepositoryJoshika userRepository;

    @InjectMocks
    private UsersServiceImplJoshika usersService;

    private Users testUser1;
    private Users testUser2;
    private Users testUser3;

    @BeforeEach
    void setUp() {
        // Setup test user 1
        testUser1 = new Users();
        testUser1.setUserId(1);
        testUser1.setName("Joshika Patel");
        testUser1.setEmail("joshika@example.com");
        testUser1.setBalance(15000.0);
        testUser1.setAddressId(401);
        testUser1.setCreatedAt(LocalDateTime.of(2024, 1, 10, 8, 0));

        // Setup test user 2
        testUser2 = new Users();
        testUser2.setUserId(2);
        testUser2.setName("Arun Verma");
        testUser2.setEmail("arun.verma@example.com");
        testUser2.setBalance(8500.0);
        testUser2.setAddressId(402);
        testUser2.setCreatedAt(LocalDateTime.of(2024, 2, 15, 10, 30));

        // Setup test user 3
        testUser3 = new Users();
        testUser3.setUserId(3);
        testUser3.setName("Sneha Reddy");
        testUser3.setEmail("sneha.reddy@example.com");
        testUser3.setBalance(12000.0);
        testUser3.setAddressId(403);
        testUser3.setCreatedAt(LocalDateTime.of(2024, 3, 20, 14, 15));
    }

    @Test
    void testGetAllUsers_Success() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1, testUser2, testUser3);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(3, actualUsers.size());
        assertEquals("Joshika Patel", actualUsers.get(0).getName());
        assertEquals("Arun Verma", actualUsers.get(1).getName());
        assertEquals("Sneha Reddy", actualUsers.get(2).getName());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_EmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertTrue(actualUsers.isEmpty());
        assertEquals(0, actualUsers.size());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_SingleUser() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(1, actualUsers.size());
        assertEquals("Joshika Patel", actualUsers.get(0).getName());
        assertEquals("joshika@example.com", actualUsers.get(0).getEmail());
        assertEquals(15000.0, actualUsers.get(0).getBalance());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_VerifyAllUserProperties() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(1, actualUsers.size());
        
        Users user = actualUsers.get(0);
        assertEquals(1, user.getUserId());
        assertEquals("Joshika Patel", user.getName());
        assertEquals("joshika@example.com", user.getEmail());
        assertEquals(15000.0, user.getBalance());
        assertEquals(401, user.getAddressId());
        assertNotNull(user.getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 1, 10, 8, 0), user.getCreatedAt());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_MultipleCalls() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1, testUser2);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> firstCall = usersService.getAllUsers();
        List<Users> secondCall = usersService.getAllUsers();

        // Assert
        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertEquals(2, firstCall.size());
        assertEquals(2, secondCall.size());
        assertEquals(firstCall.get(0).getUserId(), secondCall.get(0).getUserId());
        
        verify(userRepository, times(2)).findAll();
    }

    @Test
    void testGetAllUsers_VerifyRepositoryInteraction() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        usersService.getAllUsers();

        // Assert
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testGetAllUsers_CheckUserBalances() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1, testUser2, testUser3);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(3, actualUsers.size());
        assertEquals(15000.0, actualUsers.get(0).getBalance());
        assertEquals(8500.0, actualUsers.get(1).getBalance());
        assertEquals(12000.0, actualUsers.get(2).getBalance());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_CheckAddressIds() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1, testUser2, testUser3);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(401, actualUsers.get(0).getAddressId());
        assertEquals(402, actualUsers.get(1).getAddressId());
        assertEquals(403, actualUsers.get(2).getAddressId());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_OrderPreserved() {
        // Arrange - Note the order: user3, user1, user2
        List<Users> expectedUsers = Arrays.asList(testUser3, testUser1, testUser2);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert - Verify order is preserved
        assertNotNull(actualUsers);
        assertEquals(3, actualUsers.size());
        assertEquals("Sneha Reddy", actualUsers.get(0).getName());
        assertEquals("Joshika Patel", actualUsers.get(1).getName());
        assertEquals("Arun Verma", actualUsers.get(2).getName());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_CheckCreatedAtDates() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1, testUser2, testUser3);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertNotNull(actualUsers.get(0).getCreatedAt());
        assertNotNull(actualUsers.get(1).getCreatedAt());
        assertNotNull(actualUsers.get(2).getCreatedAt());
        
        assertEquals(LocalDateTime.of(2024, 1, 10, 8, 0), actualUsers.get(0).getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 2, 15, 10, 30), actualUsers.get(1).getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 3, 20, 14, 15), actualUsers.get(2).getCreatedAt());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_VerifyUserEmails() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1, testUser2, testUser3);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals("joshika@example.com", actualUsers.get(0).getEmail());
        assertEquals("arun.verma@example.com", actualUsers.get(1).getEmail());
        assertEquals("sneha.reddy@example.com", actualUsers.get(2).getEmail());
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_NullCheck() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(testUser1);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = usersService.getAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertFalse(actualUsers.isEmpty());
        assertNotNull(actualUsers.get(0));
        assertNotNull(actualUsers.get(0).getName());
        assertNotNull(actualUsers.get(0).getEmail());
        
        verify(userRepository, times(1)).findAll();
    }
}

