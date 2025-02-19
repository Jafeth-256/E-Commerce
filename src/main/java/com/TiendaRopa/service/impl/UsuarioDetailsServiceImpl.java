/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TiendaRopa.service.impl;

import com.TiendaRopa.dao.UsuarioDao;
import com.TiendaRopa.domain.Rol;
import com.TiendaRopa.domain.Usuario;
import com.TiendaRopa.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService{
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Se busca el usuario que tiene el username pasado por parámetro...
        Usuario usuario = usuarioDao.findByUsuario(username);
        
        //Se valida si se recuperó un usuario / sino lanza un error
        if (usuario==null) {
            throw new UsernameNotFoundException(username);
        }
        
        
        //Se van a recuperar los roles del usuario y se crean los roles ya como seguridad de Spring
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
           roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //Se retorna un User (de tipo UserDetails)
        return new User(usuario.getUsuario(),usuario.getContrasena(),roles);
    }
}
