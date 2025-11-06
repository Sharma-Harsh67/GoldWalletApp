package com.wallet.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.wallet.app.model.Users;
import com.wallet.dto.VirtualGoldHoldingDTOJoshika;
import com.wallet.repository.UsersRepositoryJoshika;
import com.wallet.repository.VirtualGoldRepositoryJoshika;
import com.wallet.service.VirtualGoldServiceJoshika;
@Service
public class VirtualGoldServiceImplJoshika implements VirtualGoldServiceJoshika {


public VirtualGoldServiceImplJoshika(UsersRepositoryJoshika usersRepository, VirtualGoldRepositoryJoshika goldRepo) {
		super();
		this.usersRepository = usersRepository;
		this.goldRepo = goldRepo;
	}

private UsersRepositoryJoshika usersRepository;


private VirtualGoldRepositoryJoshika goldRepo;

@Override
public List<VirtualGoldHoldingDTOJoshika> getHoldingsByName(String name) {

Users user = usersRepository.findAll().stream()
.filter(u -> u.getName().equalsIgnoreCase(name.trim()))
.findFirst()
.orElseThrow(() -> new RuntimeException("User not found with name: " + name));

return goldRepo.findByUser(user).stream()
.map(h -> new VirtualGoldHoldingDTOJoshika(
h.getHoldingId(),
h.getQuantity(),
h.getBranchId()
))
.collect(Collectors.toList());
}
}


