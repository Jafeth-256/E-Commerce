
package com.TiendaRopa.controller;

import com.TiendaRopa.domain.Usuario;
import com.TiendaRopa.service.RegistroService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/acceso")
public class UsuarioController {
    
    @Autowired 
    private RegistroService registroService;
    
    @GetMapping("/inicioSesion")
    public String InicioSesion(Model model) {
        return "/acceso/inicioSesion";
    }
    
    @GetMapping("/crearCuenta")
    public String CrearCuenta(Model model) {
        return "/acceso/crearCuenta";
    }
    
    @PostMapping("/crearUsuario")
    public String crearUsuario(Model model, Usuario usuario) 
            throws MessagingException {
        model = registroService.crearUsuario(model, usuario);
        return "/acceso/salida";
    }
    
    @GetMapping("/cambiarContrasena")
    public String CambiarContrasena(Model model) {
        return "/acceso/cambiarContrasena";
    }
    
}
