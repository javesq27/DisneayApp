package com.alkemy.proyecto.alkemy;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String imagen;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private int calificacion;

    @ManyToOne
	@JoinColumn(name = "idGenero")
    private Genero genero;


    @ManyToMany(mappedBy = "peliculas")
    private List<Personaje> personajes;

    @PrePersist 
    public void prePersist() {
        fecha = new Date();
    }

    public Pelicula() {

    }

    public Pelicula(String imagen, String titulo, Date fecha) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return this.calificacion;
    }

    public void setCalificacion(int calificacion) {
        if(calificacion >=1 && calificacion<=5) {
            this.calificacion = calificacion;
        }
        
    }

    public List<Personaje> getPersonajes() {
        return this.personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
    
}
