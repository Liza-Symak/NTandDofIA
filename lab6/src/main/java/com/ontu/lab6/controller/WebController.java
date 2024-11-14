package com.ontu.lab6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = "/index")
    public String index(Model model) {

        String[][] gameBoard = {
            {"X", "O", "X"},
            {"O", "X", " "},
            {" ", "O", "X"}
        };

        model.addAttribute("gameBoard", gameBoard);

        return "index";
    }
}
