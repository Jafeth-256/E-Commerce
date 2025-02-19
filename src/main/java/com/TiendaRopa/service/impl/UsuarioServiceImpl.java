package com.TiendaRopa.service.impl;

import com.TiendaRopa.dao.RolDao;
import com.TiendaRopa.dao.UsuarioDao;
import com.TiendaRopa.domain.Rol;
import com.TiendaRopa.domain.Usuario;
import com.TiendaRopa.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getUsuarioId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String usuario) {
        return usuarioDao.findByUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String usuario, String password) {
        return usuarioDao.findByUsuarioAndContrasena(usuario, usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String usuario, String correo) {
        return usuarioDao.findByUsuarioOrCorreo(usuario, correo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String usuario, String correo) {
        return usuarioDao.existsByUsuarioOrCorreo(usuario, correo);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        usuarioDao.save(usuario);
        if (crearRolUser) { 
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setUsuarioId(usuario.getUsuarioId());
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
