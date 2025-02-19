package com.TiendaRopa.service.impl;

import com.TiendaRopa.domain.Usuario;
import com.TiendaRopa.service.CorreoService;
import com.TiendaRopa.service.RegistroService;
import com.TiendaRopa.service.UsuarioService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private CorreoService correoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource; 

    @Override
    public Model activar(Model model, String username, String clave) {
        Usuario usuario
                = usuarioService.getUsuarioPorUsernameYPassword(username,
                        clave);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute(
                    "titulo",
                    messageSource.getMessage(
                            "registro.activar",
                            null, Locale.getDefault()));
            model.addAttribute(
                    "mensaje",
                    messageSource.getMessage(
                            "registro.activar.error",
                            null, Locale.getDefault()));
        }
        return model;
    }

    @Override
    public Model crearUsuario(Model model, Usuario usuario)
            throws MessagingException {
        String mensaje;
        if (!usuarioService.
                existeUsuarioPorUsernameOCorreo(
                        usuario.getUsuario(),
                        usuario.getCorreo())) {
            String clave = generarClave();
            usuario.setContrasena(clave);
            usuarioService.save(usuario, true);
            enviaCorreoActivar(usuario, clave);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.activacion.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getCorreo());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo",
                            null,
                            Locale.getDefault()),
                    usuario.getUsuario(), usuario.getCorreo());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    @Override
    public Model recordarUsuario(Model model, Usuario usuario)
            throws MessagingException {
        String mensaje;
        Usuario usuario2 = usuarioService.getUsuarioPorUsernameOCorreo(
                usuario.getUsuario(),
                usuario.getCorreo());
        if (usuario2 != null) {
            String clave = generarClave();
            usuario2.setContrasena(clave);
            usuarioService.save(usuario2, false);
            enviaCorreoRecordar(usuario2, clave);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.recordar.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getCorreo());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo",
                            null,
                            Locale.getDefault()),
                    usuario.getUsuario(), usuario.getCorreo());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    private String generarClave() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz";
        String numerosYSimbolos = "0123456789@%$#=+-_";

        String clave = "";
        for (int i = 0; i < 12; i++) {
            String fuente = (i % 2 == 0) ? letras : numerosYSimbolos;
            int indiceRandom = (int) (Math.random() * fuente.length());
            clave += fuente.charAt(indiceRandom);
        }
        return clave;
    }

    //Ojo cómo le lee una informacion del application.properties
    @Value("${servidor.http}")
    private String servidor;

    private void enviaCorreoActivar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(
                "registro.correo.activar",
                null, Locale.getDefault());
        mensaje = String.format(
                mensaje, servidor,
                usuario.getUsuario(), clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.activacion",
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }

    private void enviaCorreoRecordar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(""
                + "registro.correo.recordar",
                null,
                Locale.getDefault());
        mensaje = String.format(
                mensaje, servidor,
                usuario.getUsuario(), clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.recordar",
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(
                usuario.getCorreo(),
                asunto, mensaje);
    }
}
