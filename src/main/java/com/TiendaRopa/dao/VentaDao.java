/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TiendaRopa.dao;

import com.TiendaRopa.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jafet
 */
public interface VentaDao extends JpaRepository<Venta, Long>{
    
}
