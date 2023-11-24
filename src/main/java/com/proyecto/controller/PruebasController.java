package com.proyecto.controller;

import com.proyecto.domain.Oferta;
import com.proyecto.domain.Producto;
import com.proyecto.domain.Servicio;
import com.proyecto.service.OfertaService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PruebasController {

    @Autowired
    private ServicioService servicioService;
    @Autowired
    private OfertaService ofertaService;
    @Autowired
    private ProductoService productoService;
    

    @GetMapping("/pruebas/listado")
    public String listadoServicios(Model model) {
        List<Servicio> servicios = servicioService.getServicios(true); 

        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());

        return "/pruebas/listado"; 
    }
    
    @GetMapping("/pruebas/oferta")
public String listadoOfertas(Model model) {
    List<Oferta> ofertas = ofertaService.getOfertas(true);

    model.addAttribute("ofertas", ofertas);
    model.addAttribute("totalOfertas", ofertas.size());

    return "/pruebas/oferta"; 
}

@GetMapping("/pruebas/producto")
public String listadoProductos(Model model) {
    List<Producto> productos = productoService.getProductos(true); 
    model.addAttribute("productos", productos);
    model.addAttribute("totalProductos", productos.size());

    return "/pruebas/producto"; 


}
}
