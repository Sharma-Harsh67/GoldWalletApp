package com.wallet.app.service;

import java.util.List;

import com.wallet.app.dto.VirtualGoldHoldingDTO;

public interface VirtualGoldHoldingsService {
	List<VirtualGoldHoldingDTO> getHoldingsByName(String name);
}
