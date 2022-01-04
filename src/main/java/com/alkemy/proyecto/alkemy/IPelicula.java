package com.alkemy.proyecto.alkemy;

import java.util.Date;
import java.util.List;

public interface IPelicula {

    Pelicula save(Pelicula pelicula);
    void delete(int idPelicula);
    Pelicula findById(int idPelicula);
    Iterable<Object[]> getAll(); 
    Pelicula buscarTitulo(String titulo);
    Genero findByIdGenero(int idGenero);
    List<Pelicula> buscarPorGenero(Genero genero);
    List<Pelicula> buscarFechas(Date fecha1, Date fecha2);
    
}
