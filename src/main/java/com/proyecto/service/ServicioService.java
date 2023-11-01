/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;
import com.proyecto.domain.Servicio;
import java.util.List;

public interface ServicioService {
    
    public List<Servicio>getServicios(boolean activo);
    // Se obtiene un Servicio, a partir del id de un servicio

    public Servicio getServicio(Servicio servicio);

    // Se inserta un nuevo servicio si el id del servicio esta vacío
    // Se actualiza un servicio si el id del servicio NO esta vacío
    public void save(Servicio servicio);

    // Se elimina el servicio que tiene el id pasado por parámetro
    public void delete(Servicio servicio);
}