package es.unex.aos.mc_menu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.IngredientePlatoId;

@Repository
public interface IngredientePlatoRepository extends CrudRepository<IngredientePlato, IngredientePlatoId> {
    
}
