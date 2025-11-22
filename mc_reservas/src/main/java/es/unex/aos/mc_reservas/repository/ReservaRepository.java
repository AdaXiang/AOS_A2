package es.unex.aos.mc_reservas.repository;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import es.unex.aos.mc_reservas.model.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    List<Reserva> findByMesaIdAndFechaReserva(long mesaId, Date fechaReserva);
}