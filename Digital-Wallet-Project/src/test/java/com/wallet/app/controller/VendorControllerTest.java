package com.wallet.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.dto.UserTransactionDTO;
import com.wallet.app.dto.VendorTransactionDTO;
import com.wallet.app.model.Vendor;
import com.wallet.app.service.VendorService;
import com.wallet.app.service.VendorTransactionService;
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

@WebMvcTest(VendorController.class)
class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorService vendorService;

    @MockBean
    private VendorTransactionService vendorTransactionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vendor testVendor1;
    private Vendor testVendor2;
    private VendorTransactionDTO transactionDTO1;
    private VendorTransactionDTO transactionDTO2;
    private UserTransactionDTO userTransactionDTO1;
    private BranchSummaryDTO branchSummaryDTO1;

    @BeforeEach
    void setUp() {
        // Setup test vendors
        testVendor1 = new Vendor();
        testVendor1.setVendorId(1L);
        testVendor1.setVendorName("Gold Palace");
        testVendor1.setContactEmail("contact@goldpalace.com");
        testVendor1.setDescription("Premium gold vendor");
        testVendor1.setCreatedAt(LocalDateTime.now());

        testVendor2 = new Vendor();
        testVendor2.setVendorId(2L);
        testVendor2.setVendorName("Silver Store");
        testVendor2.setContactEmail("info@silverstore.com");
        testVendor2.setDescription("Silver and gold dealer");
        testVendor2.setCreatedAt(LocalDateTime.now());

        // Setup transaction DTOs
        transactionDTO1 = new VendorTransactionDTO(1L, 101, 201L, 301L, 50.5, LocalDateTime.now());
        transactionDTO2 = new VendorTransactionDTO(2L, 102, 202L, 302L, 75.0, LocalDateTime.now());
        
        // Setup user transaction DTO
        userTransactionDTO1 = new UserTransactionDTO();
        
        // Setup branch summary DTO
      
    }

    @Test
    void testGetAllVendors_Success() throws Exception {
        // Arrange
        List<Vendor> vendorsList = Arrays.asList(testVendor1, testVendor2);
        when(vendorService.getAllVendors()).thenReturn(vendorsList);

        // Act & Assert
        mockMvc.perform(get("/api/vendors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].vendorId").value(1))
                .andExpect(jsonPath("$[0].vendorName").value("Gold Palace"))
                .andExpect(jsonPath("$[0].contactEmail").value("contact@goldpalace.com"))
                .andExpect(jsonPath("$[1].vendorId").value(2))
                .andExpect(jsonPath("$[1].vendorName").value("Silver Store"));
        
        verify(vendorService, times(1)).getAllVendors();
    }

    @Test
    void testGetAllVendors_EmptyList() throws Exception {
        // Arrange
        when(vendorService.getAllVendors()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/vendors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(vendorService, times(1)).getAllVendors();
    }

    @Test
    void testGetVendorById_Success() throws Exception {
        // Arrange
        when(vendorService.getVendorById(1L)).thenReturn(testVendor1);

        // Act & Assert
        mockMvc.perform(get("/api/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorId").value(1))
                .andExpect(jsonPath("$.vendorName").value("Gold Palace"))
                .andExpect(jsonPath("$.contactEmail").value("contact@goldpalace.com"))
                .andExpect(jsonPath("$.description").value("Premium gold vendor"));
        
        verify(vendorService, times(1)).getVendorById(1L);
    }

    @Test
    void testGetVendorById_NotFound() throws Exception {
        // Arrange
        when(vendorService.getVendorById(999L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/api/vendors/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        
        verify(vendorService, times(1)).getVendorById(999L);
    }

    @Test
    void testGetTransactionsByVendor_Success() throws Exception {
        // Arrange
        List<VendorTransactionDTO> transactions = Arrays.asList(transactionDTO1, transactionDTO2);
        when(vendorTransactionService.getTransactionsByVendorId(1L)).thenReturn(transactions);

        // Act & Assert
        mockMvc.perform(get("/api/vendors/1/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].transactionId").value(1))
                .andExpect(jsonPath("$[0].userId").value(101))
                .andExpect(jsonPath("$[0].quantity").value(50.5));
        
        verify(vendorTransactionService, times(1)).getTransactionsByVendorId(1L);
    }

    @Test
    void testGetTransactionsByVendor_EmptyList() throws Exception {
        // Arrange
        when(vendorTransactionService.getTransactionsByVendorId(anyLong()))
                .thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/vendors/1/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(vendorTransactionService, times(1)).getTransactionsByVendorId(1L);
    }

    @Test
    void testGetTransactionsByVendorName_Success() throws Exception {
        // Arrange
        List<VendorTransactionDTO> transactions = Arrays.asList(transactionDTO1);
        when(vendorTransactionService.getTransactionsByVendorName("Gold Palace"))
                .thenReturn(transactions);

        // Act & Assert
        mockMvc.perform(get("/api/vendors/transactions")
                .param("vendorName", "Gold Palace")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].transactionId").value(1));
        
        verify(vendorTransactionService, times(1)).getTransactionsByVendorName("Gold Palace");
    }

    @Test
    void testGetTransactionsByVendorName_EmptyList() throws Exception {
        // Arrange
        when(vendorTransactionService.getTransactionsByVendorName(anyString()))
                .thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/vendors/transactions")
                .param("vendorName", "NonExistent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(vendorTransactionService, times(1)).getTransactionsByVendorName("NonExistent");
    }

    @Test
    void testGetUserTransactions_Success() throws Exception {
        // Arrange
        List<UserTransactionDTO> userTransactions = Arrays.asList(userTransactionDTO1);
        when(vendorTransactionService.getTransactionsByUserName("John Doe"))
                .thenReturn(userTransactions);

        // Act & Assert
        mockMvc.perform(get("/api/vendors/user-transactions")
                .param("userName", "John Doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        
        verify(vendorTransactionService, times(1)).getTransactionsByUserName("John Doe");
    }

    @Test
    void testGetUserTransactions_EmptyList() throws Exception {
        // Arrange
        when(vendorTransactionService.getTransactionsByUserName(anyString()))
                .thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/vendors/user-transactions")
                .param("userName", "Jane Doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testGetBranchSummary_Success() throws Exception {
        // Arrange
        List<BranchSummaryDTO> branchSummaries = Arrays.asList(branchSummaryDTO1);
        when(vendorService.getBranchSummary()).thenReturn(branchSummaries);

        // Act & Assert
        mockMvc.perform(get("/api/vendors/branch-summary")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].branchId").value(1))
                .andExpect(jsonPath("$[0].quantity").value(100.0))
                .andExpect(jsonPath("$[0].branch_name").value("Main Branch"));
        
        verify(vendorService, times(1)).getBranchSummary();
    }

    @Test
    void testGetBranchSummary_EmptyList() throws Exception {
        // Arrange
        when(vendorService.getBranchSummary()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/vendors/branch-summary")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(vendorService, times(1)).getBranchSummary();
    }
}