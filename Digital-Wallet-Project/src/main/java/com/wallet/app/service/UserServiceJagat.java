package com.wallet.app.service;

import com.wallet.app.dto.AddressDTO;
import com.wallet.app.dto.UserDTOJagat;
import com.wallet.app.model.AddressJagat;
import com.wallet.app.model.UserJagat;
import com.wallet.app.repository.AddressRepositoryJagat;
import com.wallet.app.repository.UserRepositoryJagat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceJagat {

    private final UserRepositoryJagat userRepository;
    private final AddressRepositoryJagat addressRepository;

    public UserServiceJagat(UserRepositoryJagat userRepository, AddressRepositoryJagat addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    // ✅ Get all users (basic info)
    public List<UserDTOJagat> getAllUsersSimple() {
        List<UserJagat> users = userRepository.findAll();
        return users.stream()
                .map(u -> new UserDTOJagat(u.getUserId(), u.getName(), u.getEmail(), u.getAddressId()))
                .collect(Collectors.toList());
    }

    // ✅ Get address for a user by ID
    public Optional<AddressDTO> getAddressForUser(Integer userId) {
        Optional<UserJagat> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return Optional.empty();

        Integer addressId = userOpt.get().getAddressId();
        if (addressId == null) return Optional.empty();

        Optional<AddressJagat> addrOpt = addressRepository.findById(addressId);
        return addrOpt.map(a -> new AddressDTO(
                a.getAddressId(), a.getStreet(), a.getCity(), a.getState(), a.getPostalCode(), a.getCountry()
        ));
    }

    // ✅ NEW METHOD: Get address by user name
    public Optional<AddressDTO> getAddressByUserName(String name) {
        if (name == null || name.isBlank()) return Optional.empty();

        String cleanedName = name.trim().toLowerCase();

        Optional<UserJagat> userOpt = userRepository.findAll()
                .stream()
                .filter(u -> u.getName() != null && u.getName().trim().toLowerCase().equals(cleanedName))
                .findFirst();

        if (userOpt.isEmpty()) return Optional.empty();

        Integer addressId = userOpt.get().getAddressId();
        if (addressId == null) return Optional.empty();

        return addressRepository.findById(addressId)
                .map(a -> new AddressDTO(
                        a.getAddressId(), a.getStreet(), a.getCity(), a.getState(),
                        a.getPostalCode(), a.getCountry()
                ));
    }
}