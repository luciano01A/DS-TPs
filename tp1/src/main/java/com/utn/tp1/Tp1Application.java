package com.utn.tp1;

import com.utn.tp1.entidades.*;
import com.utn.tp1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class Tp1Application {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    RubroRepository rubroRepository;
    @Autowired
    ProductoRepository productoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp1Application.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {

            Domicilio domicilio1 = Domicilio.builder()
                    .calle("San Martin")
                    .numero("290")
                    .localidad("Ciudad")
                    .build();
            Domicilio domicilio2 = Domicilio.builder()
                    .calle("Belgrano")
                    .numero("124")
                    .localidad("Ciudad")
                    .build();
            List<Domicilio> domicilios = new ArrayList<>();
            domicilios.add(domicilio1);
            domicilios.add(domicilio2);



            Producto producto1 = Producto.builder()
                    .tipo("manufacturado")
                    .tiempoEstimadoCocina(35)
                    .denominacion("Hamburguesa")
                    .precioVenta(2000)
                    .precioCompra(900)
                    .stockActual(100)
                    .stockMinimo(20)
                    .unidadMedida("gramos")
                    .receta("Pan, carne, queso, lechuga y tomate")
                    .build();
            Producto producto2 = Producto.builder()
                    .tipo("manufacturado")
                    .tiempoEstimadoCocina(25)
                    .denominacion("Pizza")
                    .precioVenta(2500)
                    .precioCompra(1000)
                    .stockActual(15)
                    .stockMinimo(10)
                    .unidadMedida("gramos")
                    .receta("masa, muzzarella, salsa")
                    .build();
            List<Producto> productos = new ArrayList<>();
            productos.add(producto1);
            productos.add(producto2);



            Rubro rubro = Rubro.builder()
                    .denominacion("Comida rápida")
                    .productos(productos)
                    .build();
            rubroRepository.save(rubro);



            DetallePedido detallePedido1 = DetallePedido.builder()
                    .cantidad(1)
                    .producto(producto1)
                    .subtotal(producto1.getPrecioVenta())
                    .build();
            DetallePedido detallePedido2 = DetallePedido.builder()
                    .cantidad(1)
                    .producto(producto2)
                    .subtotal(producto2.getPrecioVenta())
                    .build();
            List<DetallePedido> detallesPedido = new ArrayList<>();
            detallesPedido.add(detallePedido1);
            detallesPedido.add(detallePedido2);



            Factura factura = Factura.builder()
                    .numero(133)
                    .fecha(new Date())
                    .descuento(0)
                    .formaPago("Efectivo")
                    .total(2500)
                    .build();



            Pedido pedido1 = Pedido.builder()
                    .estado("En preparación")
                    .fecha(new Date())
                    .tipoEnvio("Retira")
                    .detalles(detallesPedido)
                    .factura(factura)
                    .build();
            List<Pedido> pedidos = new ArrayList<>();
            pedidos.add(pedido1);



            Cliente cliente1= Cliente.builder()
                    .nombre("Eduardo")
                    .apellido("Aguirre")
                    .telefono("2619362732")
                    .email("EAguirre@hotmail.com")
                    .domicilios(domicilios)
                    .pedidos(pedidos)
                    .build();
            clienteRepository.save(cliente1);
            List<Cliente> clientes= clienteRepository.findAll();
            for (Cliente cliente: clientes){
                System.out.println(cliente.getNombre());
            }
        };
    }
}
