package es.unex.aos.mc_ingredientes.repository;

import org.springframework.data.repository.CrudRepository;
import es.unex.aos.mc_ingredientes.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

    
}