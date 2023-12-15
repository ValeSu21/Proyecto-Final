/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

/**
 *
 * @author valer
 */
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table (name="ofertas")
public class Oferta implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_oferta")
    private long idOferta;
    private String descripcion;
    private String rutaImagen;
    private String nombre;
    private Double descuento;
    private Boolean activo;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin;
    public Oferta() {
    }

    
    
    public Oferta(String descripcion, Boolean activo) {
        this.descripcion = descripcion;
        this.activo = activo;
    }

    
}

    