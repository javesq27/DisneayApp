package com.alkemy.proyecto.alkemy.repositories;

import com.alkemy.proyecto.alkemy.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);
    
}
