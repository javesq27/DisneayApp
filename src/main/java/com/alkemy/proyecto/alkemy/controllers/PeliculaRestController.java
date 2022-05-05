package com.alkemy.proyecto.alkemy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Genero;
import com.alkemy.proyecto.alkemy.entities.Pelicula;
import com.alkemy.proyecto.alkemy.services.IPelicula;

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
public class PeliculaRestController {

    @Autowired
    public IPelicula servicePelicula;

    @GetMapping("/movies")
    public List<Pelicula> findAll() {
        return servicePelicula.findAll();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Pelicula> showDetail(@PathVariable int id) {

        Pelicula pelicula = servicePelicula.findById(id);
        return new ResponseEntity<Pelicula>(pelicula, HttpStatus.OK)
;    } 

    @PostMapping("/movies")
    public ResponseEntity<Pelicula> create(@RequestBody Pelicula pelicula, @RequestParam("archivo") MultipartFile archivo) {

        if(!archivo.isEmpty()) {
            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("src//main//resources//static//images").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                
                e.printStackTrace();
            }

            pelicula.setImagen(nombreArchivo);
            servicePelicula.save(pelicula);
           
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
        
    }

    
    @PutMapping("/movies/{id}")
    public ResponseEntity<Pelicula> update(@RequestBody Pelicula pelicula, @PathVariable int id) {

        Pelicula peliculaActualizar = servicePelicula.findById(id);
        
        peliculaActualizar.setTitulo(pelicula.getTitulo());
        peliculaActualizar.setCalificacion(pelicula.getCalificacion());
        peliculaActualizar.setImagen(pelicula.getImagen());
        peliculaActualizar.setPersonajes(pelicula.getPersonajes());

        servicePelicula.save(peliculaActualizar);

        return ResponseEntity.status(HttpStatus.OK).body(peliculaActualizar);

        
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        servicePelicula.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params= "titulo")
    public ResponseEntity<Pelicula> getTitulo(@PathVariable String titulo) {
        Pelicula pelicula = servicePelicula.buscarTitulo(titulo);
        return ResponseEntity.status(HttpStatus.FOUND).body(pelicula);
    }

    @GetMapping("/gender/{idGenero}")
    public List<Pelicula> getGenero(@PathVariable int idGenero) {
        Genero genero = servicePelicula.findByIdGenero(idGenero);

        return servicePelicula.buscarPorGenero(genero);
    }

    @GetMapping("/movies/{fecha1}/{fecha2}")
    public List<Pelicula> getFechaCreacion(@PathVariable Date fecha1, @PathVariable Date fecha2) {
        return servicePelicula.buscarFechas(fecha1, fecha2);
    }

}
