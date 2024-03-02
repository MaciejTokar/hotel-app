import dao.*;
import mapping.*;
import model.*;
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

        ReservationReportService reservationReportService = new ReservationReportService(reservationDao, reservationMapper);
        ClientReportService clientReportService = new ClientReportService(clientDao, clientMapper);
        HotelReportService hotelReportService = new HotelReportService(hotelDao,hotelMapper);
        RoomReportService roomReportService = new RoomReportService(roomDao, roomMapper, hotelDao);
        EmployeeReportService employeeReportService = new EmployeeReportService(employeeDao, employeeMapper);
        EventReportService eventReportService = new EventReportService(eventDao, eventMapper);
        ReviewReportService reviewReportService = new ReviewReportService(reviewDao, reviewMapper);
        FacilityReportService facilityReportService = new FacilityReportService(facilityDao, facilityMapper, hotelDao);
    }
}
