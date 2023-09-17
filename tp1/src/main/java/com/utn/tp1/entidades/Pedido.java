package com.utn.tp1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "total")
    private double total;
        @Column(name = "estado")
        private String estado;
        @Column(name = "tipoEnvio")
        private String tipoEnvio;

@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "pedido_id")
private Factura factura;

@OneToMany (cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
@JoinColumn (name = "pedido_id")
//Anotacion para que no de ERROR
@Builder.Default
private List<DetallePedido> detalles = new ArrayList<>();
}
