package es.unex.aos.mc_menu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_menu.model.MenuPlato;
import es.unex.aos.mc_menu.model.MenuPlatoId;

@Repository
public interface MenuPlatoRepository extends CrudRepository<MenuPlato, MenuPlatoId> {
    
}
