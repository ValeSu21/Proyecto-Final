/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.serviceimpl;

/**
 *
 * @author valer
 */

import com.proyecto.dao.ServicioDao;
import com.proyecto.domain.Servicio;
import com.proyecto.service.ServicioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioServiceImpl implements ServicioService{
    
    @Autowired
    private ServicioDao servicioDao;

    @Override
    public List<Servicio> getServicios(boolean activo) {
        var servicios = servicioDao.findAll();
        
     if (activo) {
            servicios.removeIf(e -> !e.getActivo());
        }
                return servicios;
    }
  
    @Override
    @Transactional(readOnly = true)
    public Servicio getServicio(Servicio servicio) {
        return servicioDao.findById(servicio.getIdServicio()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Servicio servicio) {
        servicioDao.save(servicio);
    }

    @Override
    @Transactional
    public void delete(Servicio servicio) {
        servicioDao.delete(servicio);
    } 
    
}
