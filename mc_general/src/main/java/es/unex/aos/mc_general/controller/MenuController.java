package es.unex.aos.mc_general.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unex.aos.mc_general.clients.MenuClient;


@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuClient menuClient;
    public MenuController(MenuClient menuClient) {
        this.menuClient = menuClient;
    }

}
