package com.autolatina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autolatina.models.Proprietarios;

public interface ProprietarioRepository extends JpaRepository<Proprietarios, Long> {
	
}
