package com.tierraViva.tierraViva.services;

import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService implements IpagoService {

    private final PagoRepository pagoRepository;
    private final IcarritoService carritoService;

    public PagoService(PagoRepository pagoRepository, IcarritoService carritoService) {
        this.pagoRepository = pagoRepository;
        this.carritoService = carritoService;
    }

    // =========================
    // ===== INIT MERCADO PAGO ==
    // =========================

    @Value("${mercadopago.access_token}")
    private String accessToken;

    @PostConstruct
    public void initMercadoPago() {
        System.out.println(">>> INIT MERCADO PAGO");
        System.out.println(">>> ACCESS TOKEN ES NULL? " + (accessToken == null));

        MercadoPagoConfig.setAccessToken(accessToken);

        System.out.println(">>> MERCADO PAGO CONFIGURADO");
    }

    // =========================
    // ===== MÉTODOS VIEJOS ====
    // =========================

    @Override
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago crearPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pagoRepository.delete(pago);
    }

    @Override
    public Pago asignarCarrito(Long idPago, Long idCarrito) {

        System.out.println(">>> ASIGNAR CARRITO");
        System.out.println(">>> ID PAGO: " + idPago);
        System.out.println(">>> ID CARRITO: " + idCarrito);

        Carrito carrito = carritoService.obtenerPorId(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Pago pago = obtenerPorId(idPago)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setCarritoPago(carrito);
        return pagoRepository.save(pago);
    }

    // ============================
    // ===== MERCADO PAGO DEMO ====
    // ============================

    public String crearPagoConMercadoPago(Long idCarrito) {

        System.out.println(">>> INICIO crearPagoConMercadoPago");
        System.out.println(">>> ID CARRITO: " + idCarrito);

        // 1. Obtener carrito
        Carrito carrito = carritoService.obtenerPorId(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        System.out.println(">>> CARRITO ENCONTRADO ID: " + carrito.getIdCarrito());

        // 2. Total del carrito
        BigDecimal total = carrito.getTotal();
        System.out.println(">>> TOTAL CARRITO: " + total);

        // 3. Item Mercado Pago
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title("Compra Tierra Viva")
                .quantity(1)
                .unitPrice(total)
                .build();

        System.out.println(">>> ITEM MERCADO PAGO CREADO");

        // 4. Crear preferencia
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(List.of(item))
                .build();

        Preference preference;
        try {
            System.out.println(">>> CREANDO PREFERENCIA EN MERCADO PAGO...");
            preference = new PreferenceClient().create(preferenceRequest);
            System.out.println(">>> PREFERENCIA CREADA ID: " + preference.getId());
        } catch (Exception e) {
            System.out.println(">>> ERROR CREANDO PREFERENCIA");
            e.printStackTrace();
            throw new RuntimeException("Error creando preferencia Mercado Pago", e);
        }

        // 5. Guardar pago en BD (SIMULADO)
        Pago pago = new Pago();
        pago.setMonto(total);
        pago.setEstadoPago("PENDIENTE");
        pago.setFechaPago(LocalDate.now());
        pago.setCarritoPago(carrito);
        pago.setMpPreferenceId(preference.getId());

        pagoRepository.save(pago);

        System.out.println(">>> PAGO GUARDADO EN BD");
        System.out.println(">>> INIT POINT: " + preference.getInitPoint());

        // 6. Retornar URL
        return preference.getInitPoint();
    }
}
