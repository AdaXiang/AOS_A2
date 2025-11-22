package es.unex.aos.mc_general.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MC-MENU")
public interface MenuClient {

    @GetMapping("/platos")
    List<Object> getPlatos();

    @GetMapping("/platos/{id}")
    Object getPlatoById(@PathVariable Long id);
}
