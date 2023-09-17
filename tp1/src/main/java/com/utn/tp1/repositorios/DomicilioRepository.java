package com.utn.tp1.repositorios;

import com.utn.tp1.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DomicilioRepository extends JpaRepository<Pedido, Long> {
}
