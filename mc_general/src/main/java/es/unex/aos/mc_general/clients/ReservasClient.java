package es.unex.aos.mc_general.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MC-RESERVAS")
public interface ReservasClient {

    @GetMapping("/reservas")
    List<Object> getReservas();

    @GetMapping("/reservas/{id}")
    Object getReservaById(@PathVariable Long id);
}