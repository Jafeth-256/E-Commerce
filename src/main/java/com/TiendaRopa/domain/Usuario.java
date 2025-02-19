package com.TiendaRopa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name="usuarios")
public class Usuario {

    @Id
    @Column(nullable = false, updatable = false, name = "usuarioId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;

//    @Column(nullable = false, length = 100)
    private String usuario;

    @Column(nullable = false, length = 100, name = "contrasena")
    private String contrasena;
    
    private String correo;

    @OneToMany
    @JoinColumn(name="usuarioId")
    List<Rol> roles;
    
    @OneToMany(mappedBy = "usuario")
    private Set<Favorito> usuarioFavoritoes;

}
 