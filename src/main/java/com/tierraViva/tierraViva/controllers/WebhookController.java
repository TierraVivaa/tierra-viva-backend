package com.tierraViva.tierraViva.controllers;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.repositories.PagoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pago")
public class WebhookController {

    private final PagoRepository pagoRepository;

    public WebhookController(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @PostMapping("/webhook")
    public void recibirWebhook(@RequestParam Map<String, String> params) {

        String paymentId = params.get("data.id");
        String type = params.get("type");

        if (!"payment".equals(type)) {
            return;
        }

        try {
            Payment payment = new PaymentClient().get(Long.parseLong(paymentId));

            String estadoMP = payment.getStatus(); // approved, rejected, pending

            Pago pago = pagoRepository
                    .findByMpPaymentId(paymentId)
                    .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

            pago.setEstadoPago(estadoMP.toUpperCase());
            pago.setMpPaymentId(paymentId);

            pagoRepository.save(pago);

        } catch (Exception e) {
            throw new RuntimeException("Error procesando webhook Mercado Pago", e);
        }
    }
}
