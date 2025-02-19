/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TiendaRopa.dao;
import com.TiendaRopa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
    
    Usuario findByUsuario(String usuario);
    
    Usuario findByUsuarioAndContrasena(String usuario, String Contrasena);

    Usuario findByUsuarioOrCorreo(String usuario, String correo);

    boolean existsByUsuarioOrCorreo(String usuario, String correo);
    
}
