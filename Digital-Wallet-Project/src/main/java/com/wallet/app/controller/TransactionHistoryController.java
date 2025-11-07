package com.wallet.app.controller;
import com.wallet.app.model.Users;
import com.wallet.app.dto.TransactionHistoryDTO;
import com.wallet.app.service.UsersService;
import com.wallet.app.service.TransactionHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/user-transactions")  
@CrossOrigin
public class TransactionHistoryController {
    private  UsersService usersService;
    private  TransactionHistoryService txService;
    public TransactionHistoryController(UsersService usersService, TransactionHistoryService txService) {
        this.usersService = usersService;
        this.txService = txService;
    }
    // -------------------- USER ENDPOINTS --------------------
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = usersService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    // -------------------- TRANSACTION HISTORY ENDPOINTS --------------------
    @GetMapping("/transactions/by-name")
    public ResponseEntity<List<TransactionHistoryDTO>> getTransactionsByUserName(@RequestParam String name) {
        return ResponseEntity.ok(txService.getTransactionsByUserName(name));
    }
}
 