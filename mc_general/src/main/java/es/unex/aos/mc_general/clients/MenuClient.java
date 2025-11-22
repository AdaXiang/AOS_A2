package es.unex.aos.mc_general.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "MC-MENU")
public interface MenuClient {

    @GetMapping("/platos")
    List<Object> getPlatos();

    @GetMapping("/platos/{id}")
    Object getPlatoById(@PathVariable Long id);

    @PostMapping("/platos/{id}/pedido")
    String realizarPedido(@PathVariable("id") Long id);
}
