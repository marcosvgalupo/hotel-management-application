package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.authentication.LoginForm;
import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.entity.Reservation;
import br.com.unifalmg.hotel.service.GuestService;
import br.com.unifalmg.hotel.service.ManagerService;
import br.com.unifalmg.hotel.service.EmployeeService;
//import br.com.unifalmg.hotel.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
public class HotelController {

    private final GuestService guestService;
    private final ManagerService managerService;
    private final EmployeeService employeeService;
//    private final ReservationService reservationService;

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

    @GetMapping("/addGuest")
    public String getAddGuest(){
        return "adicionar";
    }

    @GetMapping("/employees")
    public String employees(Model model){
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String getAddEmployee(){
        return "novo-funcionario";
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

//    @GetMapping("/reservations")
//    public String reservations(Model model){
//        List<Reservation> reservations = reservationService.getAllReservations();
//        model.addAttribute("reservations", reservations);
//        return "reservations";
//    }

    @PostMapping("/addGuest")
    public String newGuest(@RequestParam String nome,
                                   @RequestParam String sobrenome,
                                   @RequestParam String cpf,
                                   @RequestParam String telefone,
                                   @RequestParam String sexo) {
        Guest newGuest = Guest.builder()
                .name(nome)
                .last_name(sobrenome)
                .cpf(cpf)
                .cellphone(telefone)
                .gender(sexo.charAt(0))
                .build();

        guestService.saveGuest(newGuest);

        return "redirect:/guests";
    }

    @GetMapping("/deleteGuest/{id}")
    public String deleteGuest(@PathVariable Integer id) {
        guestService.deleteGuest(id);

        return "redirect:/guests";
    }



    @GetMapping("/updateGuest/{id}")
    public String updateGuest(@PathVariable Integer id, Model model) {
        Guest existingGuest = guestService.findById(id);

        model.addAttribute("guest", existingGuest);

        return "update-guest";
    }



    @PostMapping("/saveUpdatedGuest")
    public String saveUpdatedGuest(@ModelAttribute Guest updatedGuest) {
        guestService.saveGuest(updatedGuest);

        return "redirect:/guests";
    }

}