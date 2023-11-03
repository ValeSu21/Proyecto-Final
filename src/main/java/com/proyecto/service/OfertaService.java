/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;


import com.proyecto.domain.Oferta;

import java.util.List;

public interface OfertaService {
    List<Oferta> getOfertas(boolean activo);

    Oferta getOferta(Oferta oferta);

    void save(Oferta oferta);

    void delete(Oferta oferta);
}
