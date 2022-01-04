package com.alkemy.proyecto.alkemy;

import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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
public class PeliculaRestController {

    @Autowired
    public IPelicula servicePelicula;

    @GetMapping("/movies")
    public Iterable<Object[]> index() {
        return servicePelicula.getAll();
    }

    @GetMapping("/detail/{id}")
    public Pelicula showDetail(@PathVariable int id) {
        return servicePelicula.findById(id);
    } 

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public Pelicula create(@RequestBody Pelicula pelicula, @RequestParam("archivo") MultipartFile archivo) {

        if(!archivo.isEmpty()) {
            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("src//main//resources//static//images").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                
                e.printStackTrace();
            }

            pelicula.setImagen(nombreArchivo);
           
        }
        return servicePelicula.save(pelicula);
        
    }

    
    @PutMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pelicula update(@RequestBody Pelicula pelicula, @PathVariable int id) {

        Pelicula peliculaActualizar = servicePelicula.findById(id);
        
        peliculaActualizar.setTitulo(pelicula.getTitulo());
        peliculaActualizar.setCalificacion(pelicula.getCalificacion());
        peliculaActualizar.setImagen(pelicula.getImagen());
        peliculaActualizar.setPersonajes(pelicula.getPersonajes());

        return servicePelicula.save(peliculaActualizar);

        
    }

    @DeleteMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        servicePelicula.delete(id);
    }

    @GetMapping(params= "titulo")
    public Pelicula getTitulo(@PathVariable String titulo) {
        return servicePelicula.buscarTitulo(titulo);
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
