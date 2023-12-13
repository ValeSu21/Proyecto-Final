
package com.proyecto.domain;

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
    private long idServicio;
    private String descripcion;
    private String nombre_usuario;
    private String correo_electronico;
    private String numero_telefono;
    private LocalDateTime fecha_hora;
    private Boolean activo;
    
    
}
