package com.alkemy.proyecto.alkemy.services;

import java.util.Date;
import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Genero;
import com.alkemy.proyecto.alkemy.entities.Pelicula;
import com.alkemy.proyecto.alkemy.repositories.GeneroRepository;
import com.alkemy.proyecto.alkemy.repositories.PeliculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService implements IPelicula {

    @Autowired
    private PeliculaRepository repoPelicula;

    @Autowired
    private GeneroRepository repoGenero;

    @Override
    public Pelicula save(Pelicula pelicula) {
        
        return repoPelicula.save(pelicula);
    }

    @Override
    public void delete(int idPelicula) {
        repoPelicula.deleteById(idPelicula);
        
    }

    @Override
    public Pelicula findById(int idPelicula) {
        
        return repoPelicula.findById(idPelicula).orElse(null);
    }

    @Override
    public Pelicula buscarTitulo(String titulo) {
        
        return repoPelicula.findByTitulo(titulo);
    }

    @Override
    public Genero findByIdGenero(int idGenero) {
        return repoGenero.findById(idGenero).orElse(null);
    }

    @Override
    public List<Pelicula> buscarPorGenero(Genero genero) {
        
        return repoPelicula.findByGenero(genero);
    }

    @Override
    public List<Pelicula> buscarFechas(Date fecha1, Date fecha2) {
        
        return repoPelicula.findByFechaBetweenOrderByFechaDesc(fecha1, fecha2);
    }

    @Override
    public List<Pelicula> findAll() {

        return repoPelicula.findAll();
    }
    
}
