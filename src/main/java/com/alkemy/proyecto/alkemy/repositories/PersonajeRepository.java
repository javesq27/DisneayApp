package com.alkemy.proyecto.alkemy.repositories;


import com.alkemy.proyecto.alkemy.entities.Personaje;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    Personaje findByNombre(String nombre);

    Personaje findByEdad(int edad);

    
}
