package com.wallet.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.app.model.Payments;
import com.wallet.app.model.Users;
import com.wallet.app.service.PaymentsService;
import com.wallet.app.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private PaymentsService paymentsService;

    @Autowired
    private ObjectMapper objectMapper;

    private Users testUser1;
    private Users testUser2;
    private Payments testPayment1;
    private Payments testPayment2;

    @BeforeEach
    void setUp() {
        // Setup test user 1
        testUser1 = new Users();
        testUser1.setUserId(1);
        testUser1.setName("John Doe");
        testUser1.setEmail("john@example.com");
        testUser1.setBalance(1000.0);
        testUser1.setAddressId(101);
        testUser1.setCreatedAt(LocalDateTime.now());

        // Setup test user 2
        testUser2 = new Users();
        testUser2.setUserId(2);
        testUser2.setName("Jane Smith");
        testUser2.setEmail("jane@example.com");
        testUser2.setBalance(2000.0);
        testUser2.setAddressId(102);
        testUser2.setCreatedAt(LocalDateTime.now());

        // Setup test payments
        testPayment1 = new Payments();
        testPayment1.setPaymentId(1);
        testPayment1.setUser(testUser1);
        testPayment1.setAmount(500.0);

        testPayment2 = new Payments();
        testPayment2.setPaymentId(2);
        testPayment2.setUser(testUser1);
        testPayment2.setAmount(300.0);
    }

    @Test
    void testGetAllUsers_Success() throws Exception {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2);
        when(usersService.getAllUsers()).thenReturn(usersList);

        // Act & Assert
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[0].balance").value(1000.0))
                .andExpect(jsonPath("$[1].userId").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
        
        verify(usersService, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_EmptyList() throws Exception {
        // Arrange
        when(usersService.getAllUsers()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(usersService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById_Success() throws Exception {
        // Arrange
        when(usersService.getUserById(1)).thenReturn(testUser1);

        // Act & Assert
        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.balance").value(1000.0))
                .andExpect(jsonPath("$.addressId").value(101));
        
        verify(usersService, times(1)).getUserById(1);
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        // Arrange
        when(usersService.getUserById(999))
                .thenThrow(new RuntimeException("User not found"));

        // Act & Assert
        mockMvc.perform(get("/api/users/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
        
        verify(usersService, times(1)).getUserById(999);
    }

    @Test
    void testGetPaymentsByUser_Success() throws Exception {
        // Arrange
        List<Payments> paymentsList = Arrays.asList(testPayment1, testPayment2);
        when(paymentsService.getPaymentsByUserId(1)).thenReturn(paymentsList);

        // Act & Assert
        mockMvc.perform(get("/api/users/1/payments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].paymentId").value(1))
                .andExpect(jsonPath("$[0].amount").value(500.0))
                .andExpect(jsonPath("$[1].paymentId").value(2))
                .andExpect(jsonPath("$[1].amount").value(300.0));
        
        verify(paymentsService, times(1)).getPaymentsByUserId(1);
    }

    @Test
    void testGetPaymentsByUser_EmptyList() throws Exception {
        // Arrange
        when(paymentsService.getPaymentsByUserId(1)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/users/1/payments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(paymentsService, times(1)).getPaymentsByUserId(1);
    }

    @Test
    void testGetPaymentsByUser_InvalidUserId() throws Exception {
        // Arrange
        when(paymentsService.getPaymentsByUserId(anyInt()))
                .thenThrow(new RuntimeException("User not found"));

        // Act & Assert
        mockMvc.perform(get("/api/users/999/payments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
        
        verify(paymentsService, times(1)).getPaymentsByUserId(999);
    }

    @Test
    void testGetAllUsers_VerifyServiceInteraction() throws Exception {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1);
        when(usersService.getAllUsers()).thenReturn(usersList);

        // Act
        mockMvc.perform(get("/api/users"));

        // Assert
        verify(usersService, times(1)).getAllUsers();
        verifyNoMoreInteractions(usersService);
    }
}