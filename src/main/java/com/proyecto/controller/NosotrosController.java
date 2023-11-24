/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/Nosotros")
public class NosotrosController {

    // Mapea la ruta "/ubicacion" para mostrar la ubicación
    @GetMapping("/ubicacion")
    public String mostrarUbicacion(Model model) {
        model.addAttribute("latitud", 9.9281);
        model.addAttribute("longitud", -84.0907);
        return "nosotros/ubicacion";
    }

    // Mapea la ruta "/preguntas" para mostrar las preguntas frecuentes
    @GetMapping("/preguntas")
    public String mostrarPreguntas(Model model) {
        // Agrega aquí lógica para obtener las preguntas frecuentes desde tu base de datos u otro origen de datos.
        // Por ahora, usaré ejemplos estáticos.
        List<String> preguntas = Arrays.asList(
                "¿Cuáles son sus horarios de atención?",
                        
                "\nNuestros horarios de atención son de Lunes a Sábado de 8 am a 8pm.\n",
                
                "¿Ofrecen servicios a domicilio?",
                "\nNo, de momento solo atendemos a nuestros clientes en nuestra sucursal.\n",
                "¿Cómo puedo agendar una cita?",
        "Puede contactarnos al número +506 2215-2020 o bien por medio de nuestro sitio web, donde también puede consultar por la disponibilidad.",
                "¿Cúales son los métodos de pago?",
        "Puede relizar el pago en efectivo, transferencia bancaria, se aceptan tarjetas de débito y crédito.");
                // Agrega más preguntas según sea necesario.
        

        model.addAttribute("preguntas", preguntas);
        return "nosotros/preguntas";
    }
}

    