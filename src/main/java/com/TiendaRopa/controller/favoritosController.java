/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TiendaRopa.controller;

//import com.TiendaRopa.service.favoritoService;
import com.TiendaRopa.domain.Favorito;
import com.TiendaRopa.domain.Usuario;
import com.TiendaRopa.service.favoritoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/favoritos")
public class favoritosController {
    
    @Autowired
    private favoritoService favoritoService;
    
    @GetMapping("/listaFavoritos")
    public ModelAndView inicio(Model model) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(1);
        
        List<Favorito> favoritos = favoritoService.getFavoritoByUserId(usuario);
        
        return new ModelAndView("/favoritos/listaFavoritos").addObject("favoritos", favoritos);
    }
}
