package es.unex.aos.mc_reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import es.unex.aos.mc_reservas.model.Mesa;

@RestResource(path="mesas", rel="mesa")
public interface MesaRepository extends CrudRepository<Mesa, Long> {
    
}
