package es.unex.aos.mc_ingredientes.repository;

import org.springframework.data.repository.CrudRepository;
import es.unex.aos.mc_ingredientes.model.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {
    
}
