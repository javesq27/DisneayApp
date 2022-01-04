package com.alkemy.proyecto.alkemy;


public interface IPersonaje {

    Personaje save (Personaje personaje);
    void delete (int idPersonaje);
    Personaje findById(int idPersonaje);
    Iterable<Object[]> getAllPersonajes();
    Personaje buscarNombre(String nombre);
    Personaje buscarPersonajeEdad(int edad);
   
    
}
