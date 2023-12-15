/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.serviceimpl;

import com.proyecto.domain.Usuario;
import com.proyecto.service.CorreoService;
import com.proyecto.service.RegistroService;
import com.proyecto.service.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private CorreoService correoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @Override
    public Model activar(Model model, String username, String clave) {
        Usuario usuario = usuarioService.getUsuarioPorUsernameYPassword(username, clave);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("titulo", "Activación de cuenta");
            model.addAttribute("mensaje", "Error al activar la cuenta");
        }
        return model;
    }

    @Override
    public void activar(Usuario usuario, MultipartFile imagenFile) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));

        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario, false);
            usuario.setRutaImagen(firebaseStorageService.cargaImagen(imagenFile, "usuarios", usuario.getIdUsuario()));
        }
        usuarioService.save(usuario, true);
    }

    @Override
    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException {
        String mensaje;
        if (!usuarioService.existeUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo())) {
            String clave = demeClave();
            usuario.setPassword(clave);
            usuario.setActivo(false);
            usuarioService.save(usuario, true);
            enviaCorreoActivar(usuario, clave);
            mensaje = String.format("Registro exitoso, activación pendiente para %s", usuario.getCorreo());
        } else {
            mensaje = String.format("Usuario o correo ya existente: %s, %s", usuario.getUsername(), usuario.getCorreo());
        }
        model.addAttribute("titulo", "Activación de cuenta");
        model.addAttribute("mensaje", mensaje);
        return model;
    }

    @Override
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException {
        String mensaje;
        Usuario usuario2 = usuarioService.getUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo());
        if (usuario2 != null) {
            String clave = demeClave();
            usuario2.setPassword(clave);
            usuario2.setActivo(false);
            usuarioService.save(usuario2, false);
            enviaCorreoRecordar(usuario2, clave);
            mensaje = String.format("Solicitud de recuperación exitosa para %s", usuario.getCorreo());
        } else {
            mensaje = String.format("Usuario o correo no encontrado: %s, %s", usuario.getUsername(), usuario.getCorreo());
        }
        model.addAttribute("titulo", "Recuperación de cuenta");
        model.addAttribute("mensaje", mensaje);
        return model;
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        StringBuilder clave = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            clave.append(tira.charAt((int) (Math.random() * tira.length())));
        }
        return clave.toString();
    }

    private void enviaCorreoActivar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = String.format("Para activar tu cuenta, haz clic en el siguiente enlace: %s/activar/%s/%s",
                "localhost", usuario.getUsername(), clave);
        String asunto = "Activación de cuenta";
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }

    private void enviaCorreoRecordar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = String.format("Tu nueva clave es: %s", clave);
        String asunto = "Recuperación de cuenta";
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }
}
