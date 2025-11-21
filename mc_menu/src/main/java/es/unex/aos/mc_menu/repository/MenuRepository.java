package es.unex.aos.mc_menu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_menu.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    
} 