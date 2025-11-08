package com.wallet.app.service;

import com.wallet.app.dto.UsersDTOKiranmayee;
import com.wallet.app.model.Users;
import com.wallet.app.repository.UsersRepositoryKiranmayee;
import com.wallet.app.repository.PhysicalGoldTransactionsRepositoryKiranmayee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceKiranmayeeTest {

    @Mock
    private UsersRepositoryKiranmayee usersRepository;

    @Mock
    private PhysicalGoldTransactionsRepositoryKiranmayee transactionsRepository;

    @InjectMocks
    private UsersServiceKiranmayee usersService;

    private Users testUser1;
    private Users testUser2;
    private Users testUser3;

    @BeforeEach
    void setUp() {
        // Setup test user 1
        testUser1 = new Users();
        testUser1.setUserId(1);
        testUser1.setName("Kiranmayee");
        testUser1.setEmail("kiranmayee@example.com");
        testUser1.setBalance(10000.0);
        testUser1.setAddressId(301);
        testUser1.setCreatedAt(LocalDateTime.of(2024, 1, 15, 10, 30));

        // Setup test user 2
        testUser2 = new Users();
        testUser2.setUserId(2);
        testUser2.setName("Ravi Kumar");
        testUser2.setEmail("ravi.kumar@example.com");
        testUser2.setBalance(5000.0);
        testUser2.setAddressId(302);
        testUser2.setCreatedAt(LocalDateTime.of(2024, 2, 20, 14, 45));

        // Setup test user 3
        testUser3 = new Users();
        testUser3.setUserId(3);
        testUser3.setName("Priya Singh");
        testUser3.setEmail("priya.singh@example.com");
        testUser3.setBalance(7500.0);
        testUser3.setAddressId(303);
        testUser3.setCreatedAt(LocalDateTime.of(2024, 3, 10, 9, 15));
    }

    @Test
    void testGetAllUserDTOs_Success() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2, testUser3);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify first user DTO
        UsersDTOKiranmayee dto1 = result.get(0);
        assertEquals(1, dto1.getUserId());
        assertEquals("Kiranmayee", dto1.getName());
        assertEquals("kiranmayee@example.com", dto1.getEmail());
        assertEquals(301, dto1.getAddressId());
        assertEquals(10000.0, dto1.getBalance());
        assertNotNull(dto1.getCreatedAt());
        
        // Verify second user DTO
        UsersDTOKiranmayee dto2 = result.get(1);
        assertEquals(2, dto2.getUserId());
        assertEquals("Ravi Kumar", dto2.getName());
        assertEquals(5000.0, dto2.getBalance());
        
        // Verify third user DTO
        UsersDTOKiranmayee dto3 = result.get(2);
        assertEquals(3, dto3.getUserId());
        assertEquals("Priya Singh", dto3.getName());
        assertEquals(7500.0, dto3.getBalance());
        
        verify(usersRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUserDTOs_EmptyList() {
        // Arrange
        when(usersRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
        
        verify(usersRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUserDTOs_SingleUser() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        
        UsersDTOKiranmayee dto = result.get(0);
        assertEquals(1, dto.getUserId());
        assertEquals("Kiranmayee", dto.getName());
        assertEquals("kiranmayee@example.com", dto.getEmail());
        assertEquals(10000.0, dto.getBalance());
        assertEquals(301, dto.getAddressId());
        
        verify(usersRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUserDTOs_VerifyDTOMapping() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        
        // Verify that all fields are correctly mapped from Users to DTO
        UsersDTOKiranmayee dto = result.get(0);
        assertEquals(testUser1.getUserId(), dto.getUserId());
        assertEquals(testUser1.getName(), dto.getName());
        assertEquals(testUser1.getEmail(), dto.getEmail());
        assertEquals(testUser1.getAddressId(), dto.getAddressId());
        assertEquals(testUser1.getBalance(), dto.getBalance());
        assertEquals(testUser1.getCreatedAt(), dto.getCreatedAt());
    }

    @Test
    void testGetAllUserDTOs_MultipleUsersWithDifferentBalances() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2, testUser3);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify balances are correctly mapped
        assertEquals(10000.0, result.get(0).getBalance());
        assertEquals(5000.0, result.get(1).getBalance());
        assertEquals(7500.0, result.get(2).getBalance());
    }

    @Test
    void testGetAllUserDTOs_VerifyAddressIds() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2, testUser3);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify address IDs are correctly mapped
        assertEquals(301, result.get(0).getAddressId());
        assertEquals(302, result.get(1).getAddressId());
        assertEquals(303, result.get(2).getAddressId());
    }

    @Test
    void testGetAllUserDTOs_VerifyCreatedAtDates() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2, testUser3);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify created dates are correctly mapped
        assertNotNull(result.get(0).getCreatedAt());
        assertNotNull(result.get(1).getCreatedAt());
        assertNotNull(result.get(2).getCreatedAt());
        
        assertEquals(testUser1.getCreatedAt(), result.get(0).getCreatedAt());
        assertEquals(testUser2.getCreatedAt(), result.get(1).getCreatedAt());
        assertEquals(testUser3.getCreatedAt(), result.get(2).getCreatedAt());
    }

    @Test
    void testGetAllUserDTOs_VerifyRepositoryInteraction() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        usersService.getAllUserDTOs();

        // Assert
        verify(usersRepository, times(1)).findAll();
        verifyNoMoreInteractions(usersRepository);
        verifyNoInteractions(transactionsRepository);
    }

    @Test
    void testGetAllUserDTOs_MultipleCalls() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> firstCall = usersService.getAllUserDTOs();
        List<UsersDTOKiranmayee> secondCall = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertEquals(2, firstCall.size());
        assertEquals(2, secondCall.size());
        assertEquals(firstCall.get(0).getUserId(), secondCall.get(0).getUserId());
        
        verify(usersRepository, times(2)).findAll();
    }

    @Test
    void testGetAllUserDTOs_PreserveOrder() {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser3, testUser1, testUser2);
        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<UsersDTOKiranmayee> result = usersService.getAllUserDTOs();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify order is preserved
        assertEquals(3, result.get(0).getUserId());
        assertEquals(1, result.get(1).getUserId());
        assertEquals(2, result.get(2).getUserId());
        
        assertEquals("Priya Singh", result.get(0).getName());
        assertEquals("Kiranmayee", result.get(1).getName());
        assertEquals("Ravi Kumar", result.get(2).getName());
    }
}
