package com.alkemy.proyecto.alkemy.services;

import java.util.Date;
import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Genero;
import com.alkemy.proyecto.alkemy.entities.Pelicula;

public interface IPelicula {

    Pelicula save(Pelicula pelicula);
    void delete(int idPelicula);
    Pelicula findById(int idPelicula);
    List<Pelicula> findAll();
    Pelicula buscarTitulo(String titulo);
    Genero findByIdGenero(int idGenero);
    List<Pelicula> buscarPorGenero(Genero genero);
    List<Pelicula> buscarFechas(Date fecha1, Date fecha2);
    
}
