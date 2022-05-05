package com.alkemy.proyecto.alkemy.repositories;

import java.util.Date;
import java.util.List;

import com.alkemy.proyecto.alkemy.entities.Genero;
import com.alkemy.proyecto.alkemy.entities.Pelicula;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

    Pelicula findByTitulo(String titulo);

    List<Pelicula> findByGenero(Genero genero);

    List<Pelicula> findByFechaBetweenOrderByFechaDesc(Date fecha1, Date fecha2);
}
