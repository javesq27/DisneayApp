package com.alkemy.proyecto.alkemy;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    
    @Query(value = "SELECT p.imagen, p.titulo, p.fecha FROM Pelicula p")
    Iterable<Object[]> getAll();

    Pelicula findByTitulo(String titulo);

    List<Pelicula> findByGenero(Genero genero);

    List<Pelicula> findByFechaBetweenOrderByFechaDesc(Date fecha1, Date fecha2);
}
