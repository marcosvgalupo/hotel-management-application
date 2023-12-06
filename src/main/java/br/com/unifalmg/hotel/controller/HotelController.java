package br.com.unifalmg.hotel.controller;

import br.com.unifalmg.hotel.entity.*;
import br.com.unifalmg.hotel.service.GuestService;
import br.com.unifalmg.hotel.service.RoomService;
import br.com.unifalmg.hotel.service.RoomTypeService;
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
    private final RoomTypeService roomTypeService;



    //==========================================================HTML==============================================================
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/report")
    public String getReport(){
        return "report";
    }

    @GetMapping("/report2")
    public String getReport2(){
        return "report-2";
    }

    @GetMapping("/report3")
    public String getReport3(){
        return "report-3";
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

    @GetMapping("/managerAndEmployees")
    public String getEmployeesAndManager(Model model){
        List<Object[]> managers = managerService.employeeAndManager();
        model.addAttribute("managers", managers);
        return "managers-and-employees";
    }


    @GetMapping("/updateManager/{id}")
    public String updateManager(@PathVariable Integer id, Model model) {
        Manager manager = managerService.findById(id);
        model.addAttribute("manager", manager);
        return "update-manager";
    }

    @PostMapping("/saveUpdatedManager")
    public String updateRoomType(@ModelAttribute("manager") Manager manager) {
        managerService.updateManager(manager.getUsername(), manager.getPassword());
        return "redirect:/report3";

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

    @GetMapping("/mostExpensiveGuests")
    public String getGuestsWithHighestExpenses(Model model){
        List<Object[]> guests = guestService.findGuestsWithHighestExpenses();
        model.addAttribute("guests", guests);
        return "expensive-guests";
    }

    @GetMapping("/lessExpensiveGuests")
    public String getGuestsWithLowestExpenses(Model model){
        List<Object[]> guests = guestService.findGuestsWithLowestExpenses();
        model.addAttribute("guests", guests);
        return "expensive-guests";
    }

    @GetMapping("/averagePriceByGuest")
    public String AvgPriceByGuest(Model model){
        List<Object[]> guests = guestService.AvgPriceByGuest();
        model.addAttribute("guests", guests);
        return "average-lodging-price";
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
    public String addReservation(
                           @RequestParam Integer reservation_id,
                           @ModelAttribute Manager manager_id,
                           @RequestParam Integer status,
                           @ModelAttribute Lodging lodging_id,
                           @ModelAttribute Guest guest_id,
                           @RequestParam Room room_id,
                           @RequestParam String checkin_date,
                           @RequestParam String checkout_date
                                 ) {
        Reservation newReservation = Reservation.builder()
                .reservation_id(reservation_id)
                .status(status)
                .manager_id(manager_id)
                .lodging_id(lodging_id)
                .guest_id(guest_id)
                .room_id(room_id)
                .checkin_date(checkin_date)
                .checkout_date(checkout_date)
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
    public String saveUpdatedReservation(@ModelAttribute Reservation updatedReservation) {
        reservationService.saveReservation(updatedReservation);
        return "redirect:/reservation";
    }

    @GetMapping("/countReservation")
    public String countReservation(Model model){
        List<Object[]> reservations = reservationService.countReservationsByManager();
        model.addAttribute("reservations", reservations);
        return "count-reservations";
    }

    @GetMapping("/reservationsDetails")
    public String getReservationsDetails(Model model){
        List<Object[]> reservations = reservationService.getReservationDetails();
        model.addAttribute("reservations", reservations);
        return "reservations-details";
    }

    @GetMapping("/lodgingAndReservations")
    public String getLodgingAndReservations(Model model){
        List<Object[]> reservations = reservationService.getLodgingAndReservation();
        model.addAttribute("reservations", reservations);
        return "lodging-and-reservations";
    }

    @GetMapping("/reservationByRoomType")
    public String getReservationByRoomType(Model model){
        List<Object[]> rooms = roomService.reservationsByRoomType();
        model.addAttribute("rooms", rooms);
        return "reservations-roomtype";
    }

    //===========================================================ROOM=====================================================================

    @GetMapping("/statusRoom/{status}")
    public String statusRoom(Model model, @PathVariable Integer status){
        List<Room> rooms = roomService.statusRoom(status);
        model.addAttribute("rooms", rooms);
        return "status-rooms";
    }

    @GetMapping("/roomsAndTypes")
    public String getRoomsAndTypes(Model model){
        List<Object[]> rooms = roomService.roomsAndTypes();
        model.addAttribute("rooms", rooms);
        return "rooms-and-types";
    }


    @GetMapping("/updateRoomType/{id}")
    public String updateRoomType(@PathVariable Integer id, Model model) {
        RoomType roomType = roomTypeService.findById(id);
        model.addAttribute("roomType", roomType);
        return "update-room-type";
    }


    @PostMapping("/saveUpdatedRoomType")
    public String updateRoomType(@ModelAttribute("roomType") RoomType roomType) {
        roomTypeService.updateRoomTypeDescription(roomType.getCode(), roomType.getDescription());
        return "redirect:/report3";

    }



    //===========================================================REPORT=====================================================================


    @GetMapping("/guestAndReservation/{id}")
    public String guestAndReservation(Model model, @PathVariable Integer id){
        List<Object[]> guests = guestService.guestAndReservation(id);
        model.addAttribute("guests", guests);
        return "guests-reservations";
    }

    @GetMapping("/guestAndReservationByLodging/{id}")
    public String guestAndReservationByLodgingId(Model model, @PathVariable Integer id){
        List<Object[]> guests = guestService.GuestAndReservationByLodgingId(id);
        model.addAttribute("guests", guests);
        return "guests-reservations-by-lodging-id";
    }




}
