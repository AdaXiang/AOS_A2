package es.unex.aos.mc_ingredientes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_ingredientes.model.Proveedor;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {
    
}
