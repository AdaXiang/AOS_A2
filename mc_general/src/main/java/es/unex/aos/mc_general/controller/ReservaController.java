package es.unex.aos.mc_general.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import es.unex.aos.mc_general.clients.ReservasClient;

@RestController
@RequestMapping("/general")
public class ReservaController {

    private final ReservasClient reservasClient;

    public ReservaController(ReservasClient reservasClient) {
        this.reservasClient = reservasClient;
    }

    // --- RESERVAS ---
    @Tag(name = "Reserva", description = "Gestión de reservas de clientes")
    @GetMapping("/reservas")
    public List<Object> getReservas() { return reservasClient.getReservas(); }

    @Tag(name = "Reserva")
    @GetMapping("/reservas/{id}")
    public Object getReserva(@PathVariable Long id) { return reservasClient.getReservaById(id); }

    @Tag(name = "Reserva")
    @PostMapping("/reservas")
    public Object createReserva(@RequestBody Object reserva) { return reservasClient.createReserva(reserva); }

    @Tag(name = "Reserva")
    @PutMapping("/reservas/{id}")
    public Object updateReserva(@PathVariable Long id, @RequestBody Object reserva) { return reservasClient.updateReserva(id, reserva); }

    @Tag(name = "Reserva")
    @DeleteMapping("/reservas/{id}")
    public void deleteReserva(@PathVariable Long id) { reservasClient.deleteReserva(id); }


    // --- MESAS ---
    @Tag(name = "Mesa", description = "Gestión física de mesas y su ubicación")
    @GetMapping("/mesas")
    public List<Object> getMesas() { return reservasClient.getMesas(); }

    @Tag(name = "Mesa")
    @GetMapping("/mesas/{id}")
    public Object getMesa(@PathVariable Long id) { return reservasClient.getMesaById(id); }

    @Tag(name = "Mesa")
    @GetMapping("/mesas/count")
    public Long countMesas() { return reservasClient.countMesas(); }

    @Tag(name = "Mesa")
    @PostMapping("/mesas")
    public Object createMesa(@RequestBody Object mesa) { return reservasClient.createMesa(mesa); }

    @Tag(name = "Mesa")
    @PutMapping("/mesas/{id}")
    public Object updateMesa(@PathVariable Long id, @RequestBody Object mesa) { return reservasClient.updateMesa(id, mesa); }

    @Tag(name = "Mesa")
    @DeleteMapping("/mesas/{id}")
    public void deleteMesa(@PathVariable Long id) { reservasClient.deleteMesa(id); }
}