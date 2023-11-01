/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table (name="servicio")
public class Servicio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_servicio")
    private long idServicio;
    private String descripcion;
    private String rutaImagen;
    private String nombre;
    private Double precio;
    private Boolean activo;

    public Servicio() {
    }

    
    public Servicio(String descripcion, Boolean activo) {
        this.descripcion = descripcion;
        this.activo = activo;
    }
 
    
}

    

