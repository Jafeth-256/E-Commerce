/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TiendaRopa.service.impl;

import com.TiendaRopa.dao.FavoritoDao;
import com.TiendaRopa.domain.Favorito;
import com.TiendaRopa.domain.Usuario;
import com.TiendaRopa.service.favoritoService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class favoritoServiceImpl implements favoritoService{
    
    @Autowired
    private FavoritoDao favoritoDao;

    @Override
    @Transactional(readOnly=true)
    public List<Favorito> getFavoritoByUserId(Usuario usuario) {
        return (List<Favorito>)favoritoDao.findByUsuario(usuario);
    }

    @Override
    public void save(Favorito favorito) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Favorito favorito) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
