package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.service.GuestService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
public class HotelController {

    private final GuestService guestService;

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/guests")
    public String guest(Model model){
        List<Guest> guests = guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }

}
