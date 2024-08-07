package com.agustin.videojuegos.model;

import jakarta.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String imagen;
    private float precio;
    private short stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLanzamiento;
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "fk_estudio", nullable = false)
    private Estudio estudio;

    @ManyToOne
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;

}
