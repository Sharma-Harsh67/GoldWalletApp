package com.digitalgoldwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalgoldwallet.model.Users;

import java.util.Optional;
@Repository
public interface UsersRepositoryKiranmayee extends JpaRepository<Users, Integer> {
	Optional<Users> findByNameIgnoreCase(String name);
	}

