/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Oferta;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.service.OfertaService;
import com.proyecto.service.serviceimpl.FirebaseStorageServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var ofertas = ofertaService.getOfertas(false);
        model.addAttribute("ofertas", ofertas);
        model.addAttribute("totalOfertas", ofertas.size());

        return "oferta/listado";
    }
    
  @GetMapping("/nuevo")
    public String ofertaNuevo(Oferta oferta) {
        return "/oferta/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String ofertaGuardar(Oferta oferta,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            ofertaService.save(oferta);
            oferta.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "oferta", 
                            oferta.getIdOferta()));
        }
        ofertaService.save(oferta);
        return "redirect:/oferta/listado";
    }

    @GetMapping("/eliminar/{idOferta}")
    public String ofertaEliminar(Oferta oferta) {
        ofertaService.delete(oferta);
        return "redirect:/oferta/listado";
    }

    @GetMapping("/modificar/{idOferta}")
    public String ofertaModificar(Oferta oferta, Model model) {
        oferta = ofertaService.getOferta(oferta);
        model.addAttribute("oferta", oferta);
        return "oferta/modifica";
    }

}
 