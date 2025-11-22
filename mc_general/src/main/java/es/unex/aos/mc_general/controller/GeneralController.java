package es.unex.aos.mc_general.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unex.aos.mc_general.clients.IngredientesClient;
import es.unex.aos.mc_general.clients.MenuClient;
import es.unex.aos.mc_general.clients.ReservasClient;

@RestController
@RequestMapping("/general")
public class GeneralController {

    private final MenuClient menuClient;
    private final IngredientesClient ingredientesClient;
    private final ReservasClient reservasClient;

    public GeneralController(MenuClient menuClient, IngredientesClient ingredientesClient, ReservasClient reservasClient) {
        this.menuClient = menuClient;
        this.ingredientesClient = ingredientesClient;
        this.reservasClient = reservasClient;
    }

    // ----- MENÚ -----
    @GetMapping("/platos")
    public List<Object> getPlatos() {
        return menuClient.getPlatos();
    }

    @GetMapping("/platos/{id}")
    public Object getPlato(@PathVariable Long id) {
        return menuClient.getPlatoById(id);
    }

    // ----- INGREDIENTES -----
    @GetMapping("/ingredientes")
    public List<Object> getIngredientes() {
        return ingredientesClient.getIngredientes();
    }

    @GetMapping("/ingredientes/{id}")
    public Object getIngrediente(@PathVariable Long id) {
        return ingredientesClient.getIngredienteById(id);
    }

    // ----- RESERVAS -----
    @GetMapping("/reservas")
    public List<Object> getReservas() {
        return reservasClient.getReservas();
    }

    @GetMapping("/reservas/{id}")
    public Object getReserva(@PathVariable Long id) {
        return reservasClient.getReservaById(id);
    }

    // Reposición de Ingredientes (Llama a MC-INGREDIENTES)
    // POST /general/ingredientes/{id}/reposicion
    @PostMapping("/ingredientes/{id}/reposicion")
    public ResponseEntity<String> reponerIngrediente(@PathVariable Long id) {
        try {
            ingredientesClient.reponerIngrediente(id);
            return ResponseEntity.ok("Reposición solicitada correctamente para el ingrediente " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al solicitar reposición: " + e.getMessage());
        }
    }

    // Realizar Pedido de Plato (Llama a MC-MENU, quien a su vez llama a MC-INGREDIENTES)
    // POST /general/platos/{id}/pedido
    @PostMapping("/platos/{id}/pedido")
    public ResponseEntity<String> realizarPedidoPlato(@PathVariable Long id) {
        try {
            String respuesta = menuClient.realizarPedido(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            // Capturamos si MC-MENU devuelve error (ej. 400 por falta de stock)
            return ResponseEntity.badRequest().body("No se pudo realizar el pedido. Verifique el stock.");
        }
    }
}
