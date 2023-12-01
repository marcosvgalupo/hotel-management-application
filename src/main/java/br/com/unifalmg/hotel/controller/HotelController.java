package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.authentication.LoginForm;
import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.service.GuestService;
import br.com.unifalmg.hotel.service.ManagerService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
public class HotelController {

    private final GuestService guestService;
    private final ManagerService managerService;

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model){

        return "home";
    }

    @GetMapping("/guests")
    public String guest(Model model){
        List<Guest> guests = guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }

    @PostMapping(value = "/home")
    public String login(@ModelAttribute Manager manager, Model model) {
        if (managerService.autenticate(manager.getUsername(), manager.getPassword())) {
            return "/home";
        } else {
            model.addAttribute("mensagem", "Falha na autenticação");
            return "/";
        }
    }
}
