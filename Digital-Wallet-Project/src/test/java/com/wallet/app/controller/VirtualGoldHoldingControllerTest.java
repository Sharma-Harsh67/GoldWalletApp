package com.wallet.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.app.dto.VirtualGoldHoldingDTO;
import com.wallet.app.model.Users;
import com.wallet.app.service.UsersService;
import com.wallet.app.service.VirtualGoldHoldingsService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

//virtualgoldholdingcontrollertest

@WebMvcTest(VirtualGoldHoldingController.class)
class VirtualGoldHoldingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private VirtualGoldHoldingsService goldService;

    @Autowired
    private ObjectMapper objectMapper;

    private Users testUser1;
    private Users testUser2;
    private VirtualGoldHoldingDTO goldHoldingDTO1;
    private VirtualGoldHoldingDTO goldHoldingDTO2;

    @BeforeEach
    void setUp() {
        // Setup test users
        testUser1 = new Users();
        testUser1.setUserId(1);
        testUser1.setName("Alice Johnson");
        testUser1.setEmail("alice@example.com");
        testUser1.setBalance(5000.0);
        testUser1.setAddressId(201);
        testUser1.setCreatedAt(LocalDateTime.now());

        testUser2 = new Users();
        testUser2.setUserId(2);
        testUser2.setName("Bob Williams");
        testUser2.setEmail("bob@example.com");
        testUser2.setBalance(3000.0);
        testUser2.setAddressId(202);
        testUser2.setCreatedAt(LocalDateTime.now());

        // Setup gold holding DTOs
        goldHoldingDTO1 = new VirtualGoldHoldingDTO();
        goldHoldingDTO2 = new VirtualGoldHoldingDTO();
    }

    @Test
    void testGetAllUsers_Success() throws Exception {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1, testUser2);
        when(usersService.getAllUsers()).thenReturn(usersList);

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice Johnson"))
                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
                .andExpect(jsonPath("$[1].userId").value(2))
                .andExpect(jsonPath("$[1].name").value("Bob Williams"));
        
        verify(usersService, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_EmptyList() throws Exception {
        // Arrange
        when(usersService.getAllUsers()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/users")
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
        mockMvc.perform(get("/api/user-gold/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Alice Johnson"))
                .andExpect(jsonPath("$.email").value("alice@example.com"))
                .andExpect(jsonPath("$.balance").value(5000.0))
                .andExpect(jsonPath("$.addressId").value(201));
        
        verify(usersService, times(1)).getUserById(1);
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        // Arrange
        when(usersService.getUserById(999)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/users/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        
        verify(usersService, times(1)).getUserById(999);
    }

    @Test
    void testGetUserById_ValidUser() throws Exception {
        // Arrange
        when(usersService.getUserById(2)).thenReturn(testUser2);

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/users/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.name").value("Bob Williams"))
                .andExpect(jsonPath("$.balance").value(3000.0));
    }

    @Test
    void testGetHoldingsByName_Success() throws Exception {
        // Arrange
        List<VirtualGoldHoldingDTO> holdings = Arrays.asList(goldHoldingDTO1, goldHoldingDTO2);
        when(goldService.getHoldingsByName("Alice Johnson")).thenReturn(holdings);

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/holdings-by-name")
                .param("name", "Alice Johnson")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        
        verify(goldService, times(1)).getHoldingsByName("Alice Johnson");
    }

    @Test
    void testGetHoldingsByName_EmptyList() throws Exception {
        // Arrange
        when(goldService.getHoldingsByName(anyString())).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/holdings-by-name")
                .param("name", "NonExistent User")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(goldService, times(1)).getHoldingsByName("NonExistent User");
    }

    @Test
    void testGetHoldingsByName_SingleHolding() throws Exception {
        // Arrange
        List<VirtualGoldHoldingDTO> holdings = Arrays.asList(goldHoldingDTO1);
        when(goldService.getHoldingsByName("Bob Williams")).thenReturn(holdings);

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/holdings-by-name")
                .param("name", "Bob Williams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        
        verify(goldService, times(1)).getHoldingsByName("Bob Williams");
    }

    @Test
    void testGetHoldingsByName_UserNotFound() throws Exception {
        // Arrange
        when(goldService.getHoldingsByName("Unknown User"))
                .thenThrow(new RuntimeException("User not found with name: Unknown User"));

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/holdings-by-name")
                .param("name", "Unknown User")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
        
        verify(goldService, times(1)).getHoldingsByName("Unknown User");
    }

    @Test
    void testGetHoldingsByName_WithWhitespace() throws Exception {
        // Arrange
        List<VirtualGoldHoldingDTO> holdings = Arrays.asList(goldHoldingDTO1);
        when(goldService.getHoldingsByName("  Alice Johnson  ")).thenReturn(holdings);

        // Act & Assert
        mockMvc.perform(get("/api/user-gold/holdings-by-name")
                .param("name", "  Alice Johnson  ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetAllUsers_VerifyServiceInteraction() throws Exception {
        // Arrange
        List<Users> usersList = Arrays.asList(testUser1);
        when(usersService.getAllUsers()).thenReturn(usersList);

        // Act
        mockMvc.perform(get("/api/user-gold/users"));

        // Assert
        verify(usersService, times(1)).getAllUsers();
        verifyNoMoreInteractions(usersService);
    }

    @Test
    void testGetHoldingsByName_VerifyServiceInteraction() throws Exception {
        // Arrange
        List<VirtualGoldHoldingDTO> holdings = Arrays.asList(goldHoldingDTO1);
        when(goldService.getHoldingsByName("Alice Johnson")).thenReturn(holdings);

        // Act
        mockMvc.perform(get("/api/user-gold/holdings-by-name")
                .param("name", "Alice Johnson"));

        // Assert
        verify(goldService, times(1)).getHoldingsByName("Alice Johnson");
        verifyNoMoreInteractions(goldService);
    }
}
