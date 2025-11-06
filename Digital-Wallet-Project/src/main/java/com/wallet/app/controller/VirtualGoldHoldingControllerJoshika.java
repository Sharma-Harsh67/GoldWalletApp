package com.wallet.app.controller;


import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.wallet.app.dto.VirtualGoldHoldingDTOJoshika;

import com.wallet.app.service.VirtualGoldServiceJoshika;



@RestController
@RequestMapping("/api/virtual-gold")
public class VirtualGoldHoldingControllerJoshika {


public VirtualGoldHoldingControllerJoshika(VirtualGoldServiceJoshika goldService) {
		super();
		this.goldService = goldService;
	}



private VirtualGoldServiceJoshika goldService;

@GetMapping("/holdings-by-name")
public List<VirtualGoldHoldingDTOJoshika> getHoldingsByName(@RequestParam String name) {
return goldService.getHoldingsByName(name);
}
}
