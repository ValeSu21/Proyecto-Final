/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import java.util.List;

import com.proyecto.domain.Cita;

public interface CitaService {
    List<Cita> getCitas(boolean activo);

    Cita getCita(Cita cita);

    void save(Cita cita);

    void delete(Cita cita);
}