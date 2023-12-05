package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.entity.*;
import br.com.unifalmg.hotel.service.GuestService;
import br.com.unifalmg.hotel.service.RoomService;
import br.com.unifalmg.hotel.service.ManagerService;
import br.com.unifalmg.hotel.service.EmployeeService;
import br.com.unifalmg.hotel.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
public class HotelController {

    private final GuestService guestService;
    private final ManagerService managerService;
    private final EmployeeService employeeService;
    private final ReservationService reservationService;
    private final RoomService roomService;


    //==========================================================HTML==============================================================
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/report")
    public String getReport(){
        return "report";
    }

    @GetMapping("/home")
    public String getHome(Model model){
        return "home";
    }

    //=======================================================MANAGER==================================================================


    @PostMapping(value = "/home")
    public String login(@ModelAttribute Manager manager, Model model) {
        if (managerService.autenticate(manager.getUsername(), manager.getPassword())) {
            return "/home";
        } else {
            model.addAttribute("mensagem", "Falha na autenticação");
            return "/index";
        }
    }

    //=======================================================GUEST==================================================================

    @GetMapping("/guests")
    public String guest(Model model){
        List<Guest> guests = guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }

    @PostMapping("/guests")
    public String findFilteredGuests(Model model, @ModelAttribute Guest guest){
        List<Guest> guests = guestService.findByFilter(guest.getName(), guest.getLast_name(), guest.getCpf(), guest.getGender());
        model.addAttribute("guests", guests);
        return "guests";
    }

    @GetMapping("/orderedGuestsAtoZ")
    public String orderedGuestsAtoZ(Model model){
        List<Guest> guests = guestService.orderGuestsAtoZ();
        model.addAttribute("guests", guests);
        return "ordered-guests";
    }

    @GetMapping("/orderedGuestsZtoA")
    public String orderedGuestsZtoA(Model model){
        List<Guest> guests = guestService.orderGuestsZtoA();
        model.addAttribute("guests", guests);
        return "ordered-guests";
    }


    @GetMapping("/addGuest")
    public String getAddGuest(){
        return "new-guest";
    }

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



    @GetMapping("/deleteGuest/{guest_id}")
    public String getGuest(@PathVariable Integer guest_id, Model model) {
        guestService.deleteGuest(guest_id);
        List<Guest> guests = guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "redirect:/guests";
    }

    @GetMapping("/updateGuest/{id}")
    public String updateGuest(@PathVariable Integer id, Model model) {
        Guest existingGuest = guestService.findById(id);

        model.addAttribute("guests", existingGuest);

        return "update-guest";
    }



    @PostMapping("/saveUpdatedGuest")
    public String saveUpdatedGuest(@ModelAttribute Guest updatedGuest) {
        guestService.saveGuest(updatedGuest);

        return "redirect:/guests";
    }

    //===========================================================EMPLOYEES=====================================================================
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

        return "updateEmployee";
    }



    @PostMapping("/saveUpdatedEmployee")
    public String saveUpdatedEmployee(@ModelAttribute Employee updatedEmployee) {
        employeeService.saveEmployee(updatedEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/orderedEmployeesAtoZ")
    public String orderedEmployeesAtoZ(Model model){
        List<Employee> employees = employeeService.orderEmployeesAtoZ();
        model.addAttribute("employees", employees);
        return "ordered-employees";
    }

    @GetMapping("/orderedEmployeesZtoA")
    public String orderedEmployeesZtoA(Model model){
        List<Employee> employees = employeeService.orderEmployeesZtoA();
        model.addAttribute("employees", employees);
        return "ordered-employees";
    }

    @PostMapping("/employees")
    public String filteredEmployees(Model model, @ModelAttribute Employee employee){
        List<Employee> employees = employeeService.filteredEmployees(employee.getName(), employee.getLast_name(), employee.getCnh(), employee.getGender());
        model.addAttribute("employees", employees);
        return "/employees";
    }


    //===========================================================RESERVATION==================================================================

    @GetMapping("/reservation")
    public String reservations(Model model){
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @GetMapping("/new-reservation")
    public String newReservation(){
        return "new-reservation";
    }

    @PostMapping("/addReservation")
    public String addReservation(@RequestParam Guest guest_id,
                           @RequestParam Manager manager_id,
                           @RequestParam Room room_id,
                           @RequestParam Lodging lodging_id,
                           @RequestParam Date checkin_date,
                                 @RequestParam Date checkout_date
                                 ) {
        Reservation newReservation = Reservation.builder()
                .manager_id(manager_id)
                .guest_id(guest_id)
                .status(1)
                .room_id(room_id)
                .lodging_id(lodging_id)
                .checkin_date((java.sql.Date) checkin_date)
                .checkout_date((java.sql.Date) checkout_date)
                .build();

        reservationService.saveReservation(newReservation);

        return "redirect:/reservation";
    }

    @GetMapping("/cancelReservation/{id}")
    public String cancelReservation(@PathVariable Integer id) {
        reservationService.cancelReservation(id);
        return "redirect:/reservation";
    }

    @GetMapping("/updateReservation/{id}")
    public String updateReservation(@PathVariable Integer id, Model model) {
        Reservation existingReservation = reservationService.findById(id);

        model.addAttribute("reservation", existingReservation);

        return "update-reservation";
    }



    @PostMapping("/saveUpdatedReservation")
    public String saveUpdatedReservation(
            @RequestParam("checkin_date") String checkin_date,
            @RequestParam("checkout_date") String checkout_date,
            @RequestParam("id") String id) {


        Date checkinDateSql = null;
        Date checkoutDateSql = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilCheckinDate = sdf.parse(checkin_date);
            java.util.Date utilCheckoutDate = sdf.parse(checkout_date);

            checkinDateSql = new Date(utilCheckinDate.getTime());
            checkoutDateSql = new Date(utilCheckoutDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        reservationService.saveUpdateReservation(
                Integer.parseInt(id),
                (java.sql.Date) checkinDateSql,
                (java.sql.Date) checkoutDateSql
        );
        return "redirect:/reservation";
    }

    @GetMapping("/countReservation")
    public String countReservation(Model model){
        List<Object[]> reservations = reservationService.countReservationsByManager();
        model.addAttribute("reservations", reservations);
        return "count-reservations";
    }

    //===========================================================ROOM=====================================================================

    @GetMapping("/statusRoom/{status}")
    public String statusRoom(Model model, @PathVariable Integer status){
        List<Room> rooms = roomService.statusRoom(status);
        model.addAttribute("rooms", rooms);
        return "status-rooms";
    }


    //===========================================================REPORT=====================================================================


    @GetMapping("/guestAndReservation/{id}")
    public String guestAndReservation(Model model, @PathVariable Integer id){
        List<Object[]> guests = guestService.guestAndReservation(id);
        model.addAttribute("guests", guests);
        return "guests-reservations";
    }




}
