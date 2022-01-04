package com.alkemy.proyecto.alkemy;

import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PersonajeRestController {

    @Autowired
    private IPersonaje servicePersonaje;

    @GetMapping("/characters")
    public Iterable<Object[]> index() {
        return servicePersonaje.getAllPersonajes();
    }

    @GetMapping("/characters/{id}")
    public Personaje showDetail(@PathVariable int id) {
        return servicePersonaje.findById(id);
    }

    @PostMapping("/characters")
    @ResponseStatus(HttpStatus.CREATED)
    public Personaje create(@RequestBody Personaje personaje, @RequestParam("archivo") MultipartFile archivo) {

        if(!archivo.isEmpty()) {
            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("src//main//resources//static//images").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                
                e.printStackTrace();
            }

            personaje.setImagen(nombreArchivo);
           
        }
        return servicePersonaje.save(personaje);
    }

    @PutMapping("/characters/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Personaje update(@RequestBody Personaje personaje, @PathVariable int id) {
        Personaje personajeActualizar = servicePersonaje.findById(id);

        personajeActualizar.setNombre(personaje.getNombre());
        personajeActualizar.setEdad(personaje.getEdad());
        personajeActualizar.setPeso(personaje.getPeso());
        personajeActualizar.setImagen(personaje.getImagen());
        personajeActualizar.setHistoria(personaje.getHistoria());
        personajeActualizar.setPeliculas(personaje.getPeliculas());

        return servicePersonaje.save(personajeActualizar);

    }

    @DeleteMapping("/characters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        servicePersonaje.delete(id);
    }

    @GetMapping(params= "nombre")
    public Personaje getNombre(@PathVariable String nombre) {
        return servicePersonaje.buscarNombre(nombre);
    }

    @GetMapping("/characters/age/{edad}")
    public Personaje getEdad(@PathVariable int edad) {
        return servicePersonaje.buscarPersonajeEdad(edad);
    }

  


    
}
