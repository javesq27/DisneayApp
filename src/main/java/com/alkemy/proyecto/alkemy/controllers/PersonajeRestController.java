package com.alkemy.proyecto.alkemy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Personaje;
import com.alkemy.proyecto.alkemy.services.IPersonaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PersonajeRestController {

    @Autowired
    private IPersonaje servicePersonaje;

    @GetMapping("/characters")
    public List<Personaje> findAll() {
        return servicePersonaje.findAll();
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Personaje> showDetail(@PathVariable int id) {
        Personaje personaje =  servicePersonaje.findById(id);
        return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
    }

    @PostMapping("/characters")
    public ResponseEntity<Personaje> create(@RequestBody Personaje personaje, @RequestParam("archivo") MultipartFile archivo) {

        if(!archivo.isEmpty()) {
            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("src//main//resources//static//images").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                
                e.printStackTrace();
            }

            personaje.setImagen(nombreArchivo);
            servicePersonaje.save(personaje);
           
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
    }

    @PutMapping("/characters/{id}")
    public ResponseEntity<Personaje> update(@RequestBody Personaje personaje, @PathVariable int id) {
        Personaje personajeActualizar = servicePersonaje.findById(id);

        personajeActualizar.setNombre(personaje.getNombre());
        personajeActualizar.setEdad(personaje.getEdad());
        personajeActualizar.setPeso(personaje.getPeso());
        personajeActualizar.setImagen(personaje.getImagen());
        personajeActualizar.setHistoria(personaje.getHistoria());
        personajeActualizar.setPeliculas(personaje.getPeliculas());

        servicePersonaje.save(personajeActualizar);

        return ResponseEntity.status(HttpStatus.OK).body(personajeActualizar);

    }

    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        servicePersonaje.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params= "nombre")
    public ResponseEntity<Personaje> getNombre(@PathVariable String nombre) {
        Personaje personaje =  servicePersonaje.buscarNombre(nombre);
        return ResponseEntity.status(HttpStatus.FOUND).body(personaje);
    }

    @GetMapping("/characters/age/{edad}")
    public ResponseEntity<Personaje> getEdad(@PathVariable int edad) {
        Personaje personaje = servicePersonaje.buscarPersonajeEdad(edad);
        return ResponseEntity.status(HttpStatus.FOUND).body(personaje);
    }

  


    
}
