package es.unex.aos.mc_reservas.repository;

import java.time.LocalDate; // CAMBIO
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import es.unex.aos.mc_reservas.model.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    // Spring Data JPA maneja automáticamente LocalDate
    List<Reserva> findByMesaIdAndFechaReserva(long mesaId, LocalDate fechaReserva);
}