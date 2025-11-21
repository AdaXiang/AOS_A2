package es.unex.aos.mc_menu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_menu.model.TipoPlato;

@Repository
public interface TipoPlatoRepository extends CrudRepository<TipoPlato, Long> {

    
}
