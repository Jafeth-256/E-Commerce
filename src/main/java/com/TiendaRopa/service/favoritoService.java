/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TiendaRopa.service;

import com.TiendaRopa.domain.Favorito;
import com.TiendaRopa.domain.Usuario;
import java.util.List;

public interface favoritoService {
    
    public List<Favorito> getFavoritoByUserId(Usuario usuario);
    
    public void save(Favorito favorito);
    
    public void delete(Favorito favorito);
}
