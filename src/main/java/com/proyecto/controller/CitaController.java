/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;
import com.proyecto.domain.Cita;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.service.CitaService;
import com.proyecto.service.serviceimpl.FirebaseStorageServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var citas = citaService.getCitas(false);
        model.addAttribute("citas", citas);
        model.addAttribute("totalCitas", citas.size());

        return "cita/listado";
    }
    
  @GetMapping("/nuevo")
    public String citaNuevo(Cita cita) {
        return "/cita/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String citaGuardar(Cita cita){
            
        return "redirect:/cita/listado";
    }

    @GetMapping("/eliminar/{idCita}")
    public String citaEliminar(Cita cita) {
        citaService.delete(cita);
        return "redirect:/cita/listado";
    }

    @GetMapping("/modificar/{idCita}")
    public String citaModificar(Cita cita, Model model) {
        cita = citaService.getCita(cita);
        model.addAttribute("cita", cita);
        return "cita/modifica";
    }

}
 