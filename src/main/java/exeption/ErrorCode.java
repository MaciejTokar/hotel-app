package exeption;

public enum ErrorCode {
    CLIENT_ID_EXCEPTION("Client with provided id not exist"),
    HOTEL_ID_EXCEPTION("Hotel with provided id not exist"),
    EVENT_ID_EXCEPTION("Event with provided id not exist"),
    RESERVATION_ID_EXCEPTION("Reservation with provided id not exist"),
    REVIEW_ID_EXCEPTION("Review with provided id not exist"),
    ROOM_ID_EXCEPTION("Room with provided id not exist"),
    FACILITY_UPDATE_EXCEPTION("Facility has not been updated"),
    FACILITY_ROOM_EXISTENCE("Facility contains room"),
    FACILITY_ID_EXCEPTION("Facility with provided id not exist"),
    FACILITY_NOT_EXIST("Facility not exist"),
    EMPLOYEE_ID_EXCEPTION("Employee with provided id not exist");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
