package com.agustin.videojuegos.controller;

import com.agustin.videojuegos.model.Videojuego;
import com.agustin.videojuegos.services.VideojuegoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorVideojuego {

    @Autowired
    private VideojuegoService svcVideojuego;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.finAllByActivo();
            model.addAttribute("videojuegos", videojuegos);

            return "views/inicio";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/inicio")
    public String detalleVideojuego(Model model) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.finAllByActivo();
            model.addAttribute("videojuegos", videojuegos);

            return "views/inicio";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
