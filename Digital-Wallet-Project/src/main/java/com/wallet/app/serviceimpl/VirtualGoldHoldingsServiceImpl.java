package com.wallet.app.serviceimpl;
 
import java.util.List;
import java.util.stream.Collectors;
 
 
import org.springframework.stereotype.Service;
 
import com.wallet.app.model.Users;
import com.wallet.app.dto.VirtualGoldHoldingDTO;
import com.wallet.app.repository.UsersRepositoryJoshika;
import com.wallet.app.repository.VirtualGoldHoldingsRepository;
import com.wallet.app.service.VirtualGoldHoldingsService;
@Service
public class VirtualGoldHoldingsServiceImpl implements VirtualGoldHoldingsService {
 
 
public VirtualGoldHoldingsServiceImpl(UsersRepositoryJoshika usersRepository, VirtualGoldHoldingsRepository goldRepo) {
		super();
		this.usersRepository = usersRepository;
		this.goldRepo = goldRepo;
	}
 
private UsersRepositoryJoshika usersRepository;
 
 
private VirtualGoldHoldingsRepository goldRepo;
 
@Override
public List<VirtualGoldHoldingDTO> getHoldingsByName(String name) {
 
Users user = usersRepository.findAll().stream()
.filter(u -> u.getName().equalsIgnoreCase(name.trim()))
.findFirst()
.orElseThrow(() -> new RuntimeException("User not found with name: " + name));
 
return goldRepo.findByUser(user).stream()
.map(h -> new VirtualGoldHoldingDTO(
user.getName(),		
h.getHoldingId(),
h.getBranchId(),
h.getQuantity()
))
.collect(Collectors.toList());
}
}
 
 
 
 