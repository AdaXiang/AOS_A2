package es.unex.aos.mc_ingredientes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unex.aos.mc_ingredientes.model.Proveedor;
import es.unex.aos.mc_ingredientes.repository.ProveedorRepository;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
     @Autowired 
    ProveedorRepository proveedorRepository;
    
    // GET /proveedores
    @GetMapping
    public ResponseEntity<Iterable<Proveedor>> getProveedores() {
        return ResponseEntity.ok(proveedorRepository.findAll());
    }

    // GET /proveedores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        return proveedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /proveedores
    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor savedProveedor = proveedorRepository.save(proveedor);
        return ResponseEntity.status(201).body(savedProveedor);
    }

    // PUT /proveedores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetails) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            Optional.ofNullable(proveedorDetails.getNombre()).ifPresent(proveedor::setNombre);
            Optional.ofNullable(proveedorDetails.getCorreo()).ifPresent(proveedor::setCorreo);
            Optional.ofNullable(proveedorDetails.getTelefono()).ifPresent(proveedor::setTelefono);
            Proveedor updatedProveedor = proveedorRepository.save(proveedor);
            return ResponseEntity.ok(updatedProveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /proveedores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
