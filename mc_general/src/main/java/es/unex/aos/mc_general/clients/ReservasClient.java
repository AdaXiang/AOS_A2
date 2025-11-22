package es.unex.aos.mc_general.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MC-RESERVAS")
public interface ReservasClient {

    // --- RESERVAS ---
    @GetMapping("/reservas")
    List<Object> getReservas();

    @GetMapping("/reservas/{id}")
    Object getReservaById(@PathVariable("id") Long id);

    @PostMapping("/reservas")
    Object createReserva(@RequestBody Object reserva);

    @PutMapping("/reservas/{id}")
    Object updateReserva(@PathVariable("id") Long id, @RequestBody Object reserva);

    @DeleteMapping("/reservas/{id}")
    void deleteReserva(@PathVariable("id") Long id);


    // --- MESAS ---
    @GetMapping("/mesas")
    List<Object> getMesas();

    @GetMapping("/mesas/{id}")
    Object getMesaById(@PathVariable("id") Long id);

    @GetMapping("/mesas/count")
    Long countMesas();

    @PostMapping("/mesas")
    Object createMesa(@RequestBody Object mesa);

    @PutMapping("/mesas/{id}")
    Object updateMesa(@PathVariable("id") Long id, @RequestBody Object mesa);

    @DeleteMapping("/mesas/{id}")
    void deleteMesa(@PathVariable("id") Long id);
}