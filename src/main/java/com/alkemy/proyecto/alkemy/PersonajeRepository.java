package com.alkemy.proyecto.alkemy;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    @Query("SELECT p.imagen, p.nombre FROM Personaje p")
    Iterable<Object[]> getAllPersonajes();

    Personaje findByNombre(String nombre);

    Personaje findByEdad(int edad);

    
}
