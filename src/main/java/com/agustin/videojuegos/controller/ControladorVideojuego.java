package com.agustin.videojuegos.controller;

import com.agustin.videojuegos.model.Videojuego;
import com.agustin.videojuegos.services.VideojuegoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable long id) {
        try {
            Videojuego videojuego = this.svcVideojuego.findByIdAndActivo(id);
            model.addAttribute("videojuego", videojuego);

            return "views/detalle";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/busqueda")
    public String busquedaVideojuego(Model model, @RequestParam(value = "titulo", required = false) String titulo) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findByTitle(titulo);
            model.addAttribute("videojuegos", videojuegos);

            return "views/busqueda";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
