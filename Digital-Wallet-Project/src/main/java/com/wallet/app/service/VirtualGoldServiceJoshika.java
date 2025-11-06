package com.wallet.app.service;

import java.util.List;

import com.wallet.app.dto.VirtualGoldHoldingDTOJoshika;

public interface VirtualGoldServiceJoshika {
	List<VirtualGoldHoldingDTOJoshika> getHoldingsByName(String name);
}
