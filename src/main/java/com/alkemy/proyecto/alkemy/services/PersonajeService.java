package com.alkemy.proyecto.alkemy.services;


import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Personaje;
import com.alkemy.proyecto.alkemy.repositories.PersonajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeService implements IPersonaje {

    @Autowired
    private PersonajeRepository repoPersonaje;
    

    @Override
    public Personaje save(Personaje personaje) {
        
        return repoPersonaje.save(personaje);
    }

    @Override
    public void delete(int idPersonaje) {
        repoPersonaje.deleteById(idPersonaje);
        
    }

    @Override
    public Personaje findById(int idPersonaje) {
        
        return repoPersonaje.findById(idPersonaje).orElse(null);
    }

    @Override
    public Personaje buscarNombre(String nombre) {
        return repoPersonaje.findByNombre(nombre);
    }

    @Override
    public Personaje buscarPersonajeEdad(int edad) {
        
        return repoPersonaje.findByEdad(edad);
    }

    @Override
    public List<Personaje> findAll() {
        
        return repoPersonaje.findAll();
    }

   


    
}
