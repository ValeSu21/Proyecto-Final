package com.proyecto.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.proyecto.domain.Servicio;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.service.ServicioService;
import com.proyecto.service.serviceimpl.FirebaseStorageServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var servicios = servicioService.getServicios(false);
        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());

        return "servicio/listado";
    }
    
  @GetMapping("/nuevo")
    public String servicioNuevo(Servicio servicio) {
        return "/servicio/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String servicioGuardar(Servicio servicio,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            servicioService.save(servicio);
            servicio.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "servicio", 
                            servicio.getIdServicio()));
        }
        servicioService.save(servicio);
        return "redirect:/servicio/listado";
    }

    @GetMapping("/eliminar/{idServicio}")
    public String servicioEliminar(Servicio servicio) {
        servicioService.delete(servicio);
        return "redirect:/servicio/listado";
    }

    @GetMapping("/modificar/{idServicio}")
    public String servicioModificar(Servicio servicio, Model model) {
        servicio = servicioService.getServicio(servicio);
        model.addAttribute("servicio", servicio);
        return "servicio/modifica";
    }

}
 