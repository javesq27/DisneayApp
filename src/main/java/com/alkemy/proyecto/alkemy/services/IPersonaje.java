package com.alkemy.proyecto.alkemy.services;

import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Personaje;

public interface IPersonaje {

    Personaje save (Personaje personaje);
    void delete (int idPersonaje);
    Personaje findById(int idPersonaje);
    List<Personaje> findAll();
    Personaje buscarNombre(String nombre);
    Personaje buscarPersonajeEdad(int edad);
   
    
}
