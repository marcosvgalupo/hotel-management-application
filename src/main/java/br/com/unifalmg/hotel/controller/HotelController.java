package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HotelController {

    private final GuestService service;

    @GetMapping("/")
    public String getLogin(){
        return "index";
    }

    @GetMapping
    public String getHome(){
        return "home";
    }
}
