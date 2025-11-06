package com.wallet.service;

import java.util.List;

import com.wallet.dto.VirtualGoldHoldingDTOJoshika;

public interface VirtualGoldServiceJoshika {
	List<VirtualGoldHoldingDTOJoshika> getHoldingsByName(String name);
}
