package es.unex.aos.mc_general.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unex.aos.mc_general.clients.ReservasClient;


@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservasClient reservasClient;
    public ReservaController(ReservasClient reservasClient) {
        this.reservasClient = reservasClient;
    }

}
