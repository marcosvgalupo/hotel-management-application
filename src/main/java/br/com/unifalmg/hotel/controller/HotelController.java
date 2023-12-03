package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.repository.GuestRepository;
import br.com.unifalmg.hotel.service.GuestService;
import br.com.unifalmg.hotel.service.ManagerService;
import br.com.unifalmg.hotel.service.EmployeeService;
//import br.com.unifalmg.hotel.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
        return "new-guest";
    }

    @GetMapping("/employees")
    public String employees(Model model){
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String getAddEmployee(){
        return "new-employee";
    }


    @PostMapping(value = "/home")
    public String login(@ModelAttribute Manager manager, Model model) {
        if (managerService.autenticate(manager.getUsername(), manager.getPassword())) {
            return "/home";
        } else {
            model.addAttribute("mensagem", "Falha na autenticação");
            return "/index";
        }
    }

//    @GetMapping("/reservations")
//    public String reservations(Model model){
//        List<Reservation> reservations = reservationService.getAllReservations();
//        model.addAttribute("reservations", reservations);
//        return "reservations";
//    }

    @PostMapping("/addGuest")
    public String newGuest(@RequestParam String name,
                                   @RequestParam String last_name,
                                   @RequestParam String cpf,
                                   @RequestParam String cellphone,
                                   @RequestParam String gender) {
        Guest newGuest = Guest.builder()
                .name(name)
                .last_name(last_name)
                .cpf(cpf)
                .cellphone(cellphone)
                .gender(gender.charAt(0))
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

    @PostMapping("/guests")
    public String findFilteredGuests(Model model, @ModelAttribute Guest guest){
        List<GuestRepository.GuestProjection> filteredGuests = guestService.findByFilter(guest.getName(), guest.getLast_name(), guest.getCpf(), guest.getGender());
        model.addAttribute("guests", filteredGuests);
        return "redirect:/guests";
    }

    @PostMapping("/addEmployee")
    public String newEmployee(@RequestParam String name,
                           @RequestParam String last_name,
                           @RequestParam String cpf,
                           @RequestParam String cnh,
                           @RequestParam String gender,
                              @RequestParam Manager manager) {
        Employee newEmployee = Employee.builder()
                .name(name)
                .last_name(last_name)
                .cpf(cpf)
                .cnh(cnh)
                .gender(gender.charAt(0))
                .manager_id(manager)
                .build();

        employeeService.saveEmployee(newEmployee);

        return "redirect:/employees";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }



    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable Integer id, Model model) {
        Employee existingEmployee = employeeService.findById(id);

        model.addAttribute("employee", existingEmployee);

        return "update-employee";
    }



    @PostMapping("/saveUpdatedEmployee")
    public String saveUpdatedEmployee(@ModelAttribute Employee updatedEmployee) {
        employeeService.saveEmployee(updatedEmployee);
        return "redirect:/employees";
    }

}