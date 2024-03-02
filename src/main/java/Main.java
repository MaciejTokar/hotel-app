import dao.*;
import mapping.*;
import model.*;
import model.enums.CardType;
import model.enums.EventType;
import request.*;
import services.client.ClientReportService;
import services.client.ClientService;
import services.employee.EmployeeReportService;
import services.employee.EmployeeService;
import services.event.EventReportService;
import services.event.EventService;
import services.facility.FacilityReportService;
import services.facility.FacilityService;
import services.hotel.HotelReportService;
import services.hotel.HotelService;
import services.reservation.ReservationReportService;
import services.reservation.ReservationService;
import services.review.ReviewReportService;
import services.review.ReviewService;
import services.room.RoomReportService;
import services.room.RoomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();
        Room room = new Room();
        Client client = new Client();
        Reservation reservation = new Reservation();
        Employee employee = new Employee();
        Review review = new Review();
        Event event = new Event();
        Facility facility = new Facility();

        HotelDao hotelDao = new HotelDao();
        RoomDao roomDao = new RoomDao();
        ClientDao clientDao = new ClientDao();
        ReservationDao reservationDao = new ReservationDao();
        EmployeeDao employeeDao = new EmployeeDao();
        ReviewDao reviewDao = new ReviewDao();
        EventDao eventDao = new EventDao();
        FacilityDao facilityDao = new FacilityDao();

        ReservationMapper reservationMapper = new ReservationMapper();
        ClientMapper clientMapper = new ClientMapper();
        HotelMapper hotelMapper = new HotelMapper();
        RoomMapper roomMapper = new RoomMapper();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        EventMapper eventMapper = new EventMapper();
        ReviewMapper reviewMapper = new ReviewMapper();
        FacilityMapper facilityMapper = new FacilityMapper();


        HotelService hotelService = new HotelService(hotelDao, hotelMapper);
        RoomService roomService = new RoomService(roomDao, hotelDao, roomMapper);
        ClientService clientService = new ClientService(clientDao, clientMapper);
        ReservationService reservationService = new ReservationService(reservationDao, roomDao, clientDao, reservationMapper);
        EmployeeService employeeService = new EmployeeService(employeeDao, hotelDao, employeeMapper);
        EventService eventService = new EventService(eventDao, hotelDao, eventMapper);
        ReviewService reviewService = new ReviewService(reviewDao, clientDao, hotelDao, reviewMapper);
        FacilityService facilityService = new FacilityService(facilityDao, roomDao, facilityMapper);



        HotelRequest hotelRequest = HotelRequest.builder()
                .name("HotelSave")
                .address("Save 2")
                .build();

        HotelRequest hotelRequest1 = HotelRequest.builder()
                .name("Hotel UPDATE TEST")
                .address("Sortowana 2")
                .build();

        RoomRequest roomRequest = RoomRequest.builder()
                .type("Pair")
                .number("20")
                .personCount(2)
                .price(BigDecimal.valueOf(300))
                .hotelId(1L)
                .build();

        RoomRequest roomRequest1 = RoomRequest.builder()
                .type("TestUpdateRefactor2")
                .number("31")
                .personCount(2)
                .price(BigDecimal.valueOf(500))
                .hotelId(11L)
                .build();

        RoomRequest roomRequest2 = RoomRequest.builder()
                .type("Group")
                .number("40")
                .personCount(5)
                .price(BigDecimal.valueOf(700))
                .hotelId(1L)
                .build();

        RoomRequest roomRequest3 = RoomRequest.builder()
                .type("Group")
                .number("50")
                .personCount(5)
                .bathroom(true)
                .price(BigDecimal.valueOf(800))
                .hotelId(1425424L)
                .build();

        RoomRequest roomRequest4 = RoomRequest.builder()
                .type("Pair")
                .number("50")
                .personCount(2)
                .price(BigDecimal.valueOf(350))
                .hotelId(12L)
                .build();

        RoomRequest roomRequest5 = RoomRequest.builder()
                .type("Pair")
                .number("50")
                .bathroom(true)
                .personCount(2)
                .price(BigDecimal.valueOf(350))
                .hotelId(12L)
                .build();

        ClientRequest clientRequest = ClientRequest.builder()
                .name("SAVEPOZMIANIE")
                .surname("Kowalski")
                .mail("kowalski@gmail.com")
                .pesel("012345678911")
                .phone("123123123")
                .build();

        ClientRequest clientRequest1 = ClientRequest.builder()
                .name("NEXTTEST")
                .surname("Barszcz")
                .mail("test@gmail.com")
                .pesel("09876543210")
                .phone("000111222")
                .cardType(CardType.GOLD)
                .build();

        ClientRequest clientRequest2 = ClientRequest.builder()
                .name("Bartosz")
                .surname("Test")
                .mail("kowalski@gmail.com")
                .pesel("11111122334")
                .phone("999888777")
                .build();

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .fromDate(LocalDate.of(2024, 1, 20))
                .toDate(LocalDate.of(2024, 1, 25))
                .roomsId(List.of(3L, 4L, 2L))
                .clientId(65757L)
                .build();

        ReservationRequest reservationRequest1 = ReservationRequest.builder()
                .fromDate(LocalDate.of(2024, 3, 1))
                .toDate(LocalDate.of(2024, 3, 5))
                .roomsId(List.of(3L))
                .clientId(6L)
                .build();

        ReservationRequest reservationRequest2 = ReservationRequest.builder()
                .fromDate(LocalDate.of(2024, 2, 1))
                .toDate(LocalDate.of(2024, 2, 4))
                .roomsId(List.of(4L))
                .clientId(26L)
                .build();

        ReservationRequest reservationRequest3 = ReservationRequest.builder()
                .fromDate(LocalDate.of(2024, 1, 15))
                .toDate(LocalDate.of(2024, 1, 19))
                .roomsId(List.of(3L))
                .clientId(27L)
                .build();

        ReservationRequest reservationRequest4 = ReservationRequest.builder()
                .fromDate(LocalDate.of(2024, 1, 1))
                .toDate(LocalDate.of(2024, 1, 5))
                .roomsId(List.of(4L))
                .clientId(29L)
                .build();

        ReservationRequest reservationRequest5 = ReservationRequest.builder()
                .fromDate(LocalDate.of(2024, 3, 11))
                .toDate(LocalDate.of(2024, 3, 13))
                .roomsId(List.of(3L))
                .clientId(21L)
                .build();

        ReservationUpdateRequest reservationUpdateRequest = ReservationUpdateRequest.builder()
                .fromDate(LocalDate.of(2024, 1, 20))
                .toDate(LocalDate.of(2024, 1, 25))
                .roomId(3L)
                .clientId(6L)
                .build();

        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .name("TESTSAVE")
                .surname("Pracownik")
                .mail("pracownikHotel@gmail.com")
                .phone("000111222")
                .salary(BigDecimal.valueOf(5000))
                .hotelId(1L)
                .build();

        EmployeeRequest employeeRequest1 = EmployeeRequest.builder()
                .name("TESTUPDATE")
                .surname("Janowski")
                .mail("Janowski@gmail.com")
                .phone("777888999")
                .salary(BigDecimal.valueOf(3000))
                .hotelId(1L)
                .build();

        EmployeeRequest employeeRequest2 = EmployeeRequest.builder()
                .name("Mirek")
                .surname("Kelner")
                .mail("Kelner@gmail.com")
                .phone("888999777")
                .salary(BigDecimal.valueOf(3500))
                .hotelId(12L)
                .build();

        EmployeeRequest employeeRequest3 = EmployeeRequest.builder()
                .name("Kamil")
                .surname("Limak")
                .mail("Limak@gmail.com")
                .phone("333444555")
                .salary(BigDecimal.valueOf(5200))
                .hotelId(12L)
                .build();

        RoomRequest roomRequestUpdate = RoomRequest.builder()
                .type("Pair")
                .number("20")
                .personCount(2)
                .price(BigDecimal.valueOf(300))
                .hotelId(1L)
                .bathroom(true)
                .build();

        ReviewRequest reviewRequest = ReviewRequest.builder()
                .date(LocalDate.of(2024, 2, 10))
                .description("Super hotel")
                .outcome(5)
                .clientId(6L)
                .hotelId(1L)
                .build();

        ReviewRequest reviewRequest2 = ReviewRequest.builder()
                .date(LocalDate.of(2024, 1, 10))
                .description("Dramat")
                .outcome(1)
                .clientId(6L)
                .hotelId(1L)
                .build();

        ReviewRequest reviewRequest3 = ReviewRequest.builder()
                .date(LocalDate.of(2024, 2, 15))
                .description("Jedzenie klasa")
                .outcome(4)
                .clientId(1L)
                .hotelId(1557L)
                .build();

        ReviewRequest reviewRequest4 = ReviewRequest.builder()
                .date(LocalDate.of(2024, 1, 18))
                .description("TEST UPDATE")
                .outcome(5)
                .clientId(28L)
                .hotelId(2L)
                .build();

        EventRequest eventRequest = EventRequest.builder()
                .date(LocalDate.of(2024, 3, 16))
                .personCount(50)
                .hotelId(1L)
                .eventType(Set.of(EventType.WEEDING, EventType.COMPANY_PARTY))
                .build();

        EventRequest eventRequest1 = EventRequest.builder()
                .date(LocalDate.of(2024, 2, 16))
                .personCount(50)
                .hotelId(1L)
                .eventType(Set.of(EventType.WORKSHOPS, EventType.CONCERT))
                .build();

        EventRequest eventRequest2 = EventRequest.builder()
                .date(LocalDate.of(2024, 2, 16))
                .personCount(25)
                .hotelId(1L)
                .eventType(Set.of(EventType.CONCERT))
                .build();

        FacilityRequest facilityRequest = FacilityRequest.builder()
                .name("---")
                .description("---")
                .roomsId(List.of())
                .build();


//        clientService.saveClient(clientRequest);
//        clientService.updateClient(5L, clientRequest1);
//        clientService.deleteClient(5L);
//        employeeService.saveEmployee(employeeRequest);
//        employeeService.updateEmployee(23L, employeeRequest1);
//        employeeService.deleteEmployee(30L);
//        eventService.saveEvent(eventRequest);
//        eventService.saveEvent(eventRequest);
//        eventService.saveEvent(eventRequest1);
//        eventService.updateEvent(32L, eventRequest2);
//        eventService.deleteEvent(33L);
//        hotelService.saveHotel(hotelRequest);
//        hotelService.updateHotel(2L, hotelRequest1);
//        hotelService.deleteHotel(37L);
//        reservationService.updateReservation(1L, reservationUpdateRequest);
//        reservationService.deleteReservation(2L);
//        reviewService.saveReview(reviewRequest3);
//        reviewService.updateReview(41L, reviewRequest3);
//        reviewService.deleteReview(42L);
//        roomService.updateRoom(4L, roomRequest1);
//        roomService.deleteRoom(45L);
//        reservationService.saveReservation(reservationRequest);
//        roomService.saveRoom(roomRequest3);

        ReservationReportService reservationReportService = new ReservationReportService(reservationDao, reservationMapper);
        ClientReportService clientReportService = new ClientReportService(clientDao, clientMapper);
        HotelReportService hotelReportService = new HotelReportService(hotelDao,hotelMapper);
        RoomReportService roomReportService = new RoomReportService(roomDao, roomMapper, hotelDao);
        EmployeeReportService employeeReportService = new EmployeeReportService(employeeDao, employeeMapper);
        EventReportService eventReportService = new EventReportService(eventDao, eventMapper);
        ReviewReportService reviewReportService = new ReviewReportService(reviewDao, reviewMapper);
        FacilityReportService facilityReportService = new FacilityReportService(facilityDao, facilityMapper, hotelDao);


//        System.out.println(roomReportService.roomAmenity());

//        System.out.println(reservationReportService.searchReservation(LocalDate.of(2024, 1, 20), LocalDate.of(2024, 1, 25)));
//        System.out.println(employeeReportService.searchEmployee("Janowski"));
//        System.out.println(clientReportService.searchClientByName("Bartosz"));
//        System.out.println(clientReportService.sortedList());
//        System.out.println(eventReportService.sortedList());
//        System.out.println(roomReportService.roomFilter("HotelZRequest", LocalDate.of(2024, 3, 11), LocalDate.of(2024, 3, 15), false, "pair", 2, BigDecimal.valueOf(200), BigDecimal.valueOf(600)));
//        System.out.println(roomReportService.searchRoom("Pair"));
//        System.out.println(roomReportService.searchRoom(5));
//        System.out.println(reviewReportService.clientOutcome());
//        System.out.println(reviewReportService.avgOutcome());
//        System.out.println(reviewReportService.sortedReview());
//        System.out.println(reviewReportService.searchReview(6L));
//        System.out.println(clientReportService.calculatePriceForReservation(6L, 1L, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 6)));
//        System.out.println(reservationReportService.earnedByHotel());
//        System.out.println(reservationReportService.earnedByCompany());
//        System.out.println(reservationReportService.sortedList());
//        System.out.println(reservationReportService.clientPayments());
//        System.out.println(reservationReportService.earnedByHotelMonthly(2));
//        System.out.println(reservationReportService.clientReservations());
//        System.out.println(hotelReportService.searchHotelByAddress("Delete 2"));
//        System.out.println(hotelReportService.searchHotelByName("HotelUpdate"));
//        System.out.println(hotelReportService.roomWithSpecificAmenity(List.of("Klimatyzacja")));
//        System.out.println(eventReportService.sumEvent());
//        System.out.println(employeeReportService.avgSalary());
//        facilityService.deleteFacilityOfRoom(9l, 32525l);
//        System.out.println(facilityReportService.findPopularFacility());

    }
}
