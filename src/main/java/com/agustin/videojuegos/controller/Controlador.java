package com.agustin.videojuegos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping(value = "/")
    public String index(Model model) {
        String saludo = "Hola Agustin";
        model.addAttribute("saludo", saludo);
        return "index";
    }
}
