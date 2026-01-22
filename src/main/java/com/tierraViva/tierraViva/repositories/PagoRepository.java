package com.tierraViva.tierraViva.repositories;

import com.tierraViva.tierraViva.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByMpPaymentId(String mpPaymentId);
}
