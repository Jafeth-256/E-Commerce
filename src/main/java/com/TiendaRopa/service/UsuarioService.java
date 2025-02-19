package com.TiendaRopa.service;

import com.TiendaRopa.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getUsuarios();

    public Usuario getUsuario(Usuario usuario);

    public Usuario getUsuarioPorUsername(String usuario);

    public Usuario getUsuarioPorUsernameYPassword(String usuario, String password);

    public Usuario getUsuarioPorUsernameOCorreo(String usuario, String correo);

    public boolean existeUsuarioPorUsernameOCorreo(String usuario, String correo);

    public void save(Usuario usuario, boolean crearRolUser);

    public void delete(Usuario usuario);
}
