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
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table (name="cita")
public class Cita implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_cita")
    private long idCita;
    private String nombre;
    private String id_servicio;
    private LocalDateTime fecha_hora;
    private String correo_electronico;
    private Boolean activo;
    private String numero_telefono;

    public Cita(String id_servicio, Boolean activo) {
        this.id_servicio = id_servicio;
        this.activo = activo;
    }

    public Cita() {
    }
    
    
}
   