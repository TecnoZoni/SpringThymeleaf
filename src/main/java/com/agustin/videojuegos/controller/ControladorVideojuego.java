package com.agustin.videojuegos.controller;

import com.agustin.videojuegos.model.Videojuego;
import com.agustin.videojuegos.services.CategoriaService;
import com.agustin.videojuegos.services.EstudioService;
import com.agustin.videojuegos.services.VideojuegoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorVideojuego {

    @Autowired
    private VideojuegoService svcVideojuego;
    @Autowired
    private CategoriaService svcCategoria;
    @Autowired
    private EstudioService svcEstudio;

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

    @GetMapping("/crud")
    public String crudVideojuego(Model model) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAll();
            model.addAttribute("videojuegos", videojuegos);
            return "views/crud";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("categorias", this.svcCategoria.findAll());
            model.addAttribute("estudios", this.svcEstudio.findAll());

            Videojuego videojuego = (id == 0) ? new Videojuego() : this.svcVideojuego.findById(id);
            videojuego.setId(id);
            model.addAttribute("videojuego", videojuego);

            return "views/formulario/videojuego";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/formulario/videojuego/{id}")
    public String guardarVideojuego(@ModelAttribute("videojuego") Videojuego videojuego, Model model, @PathVariable Long id) {
        try {
            if (id == 0) {
                this.svcVideojuego.saveOne(videojuego);
            } else {
                this.svcVideojuego.updateOne(videojuego, id);
            }

            return "redirect:/crud";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    /**
     * Resetear tabla de videojuegos
     *
     */
}
