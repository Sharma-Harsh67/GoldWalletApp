package com.wallet.app.controller;
 
import com.wallet.app.dto.TransactionQuantityDTO;
import com.wallet.app.dto.UsersDTOKiranmayee;
import com.wallet.app.model.Users;
import com.wallet.app.service.PhysicalGoldTransactionsService;
import com.wallet.app.service.UsersService;
import com.wallet.app.service.UsersServiceKiranmayee;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class PhysicalGoldTransactionsController {
 
   
    private final PhysicalGoldTransactionsService transactionService;
    private final UsersService usersService;
 
    public PhysicalGoldTransactionsController(
        UsersServiceKiranmayee userService,
        PhysicalGoldTransactionsService transactionService,
        UsersService usersService
    ) {
      
        this.transactionService = transactionService;
        this.usersService=usersService;
    }
 
    
    @GetMapping("/users/")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
 
    
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = usersService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
 
    
    @GetMapping("/physical-transactions/quantities-by-name")
    public ResponseEntity<List<TransactionQuantityDTO>> getQuantitiesWithUserName(@RequestParam String name) {
        return ResponseEntity.ok(transactionService.getQuantitiesWithUserName(name));
    }
    
}
 