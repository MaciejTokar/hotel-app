package services.facility;

import dao.FacilityDao;
import dao.RoomDao;
import model.Facility;
import model.Room;
import request.FacilityRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FacilityService {
    private FacilityDao facilityDao;
    private RoomDao roomDao;

    public FacilityService(FacilityDao facilityDao, RoomDao roomDao) {
        this.facilityDao = facilityDao;
        this.roomDao = roomDao;
    }

    public void saveFacility(FacilityRequest facilityRequest) {
        Facility facility = new Facility();
        upsertFacility(facility, facilityRequest);

        facilityDao.save(facility);
    }

    public void updateFacility(Long id, FacilityRequest facilityRequest) {
        Facility facility = facilityDao.getById(id);
        upsertFacility(facility, facilityRequest);

        facilityDao.update(facility);
    }

    public void deleteFacility(Long id) {
        Facility facility = facilityDao.getById(id);
        deleteValidation(facility);
        facilityDao.delete(facility);
    }

    private void deleteValidation(Facility facility) {
        Optional.ofNullable(facility)
                .map(Facility::getRooms)
                .ifPresent(rooms -> {
                    if (!rooms.isEmpty()) {
                        throw new RuntimeException("Ma podpięte roomy");
                    }
                });
    }

    public void deleteFacilityOfRoom(Long facilityId, Long roomId) {
        Facility facility = facilityDao.getById(facilityId);

        Optional<Room> roomToRemove = facility.getRooms().stream()
                .filter(r -> r.getId().equals(roomId))
                .findAny();

        if (roomToRemove.isPresent()) {
            facilityDao.deleteFacilityOfRoom(facilityId, roomId);
        } else {
            throw new RuntimeException("Pokój lub obiekt Facility nie zostały znalezione dla podanych identyfikatorów.");
        }
    }

    private void upsertFacility(Facility facility, FacilityRequest facilityRequest) {
        facility.setDescription(facilityRequest.getDescription());
        facility.setName(facilityRequest.getName());

        List<Room> rooms = facilityRequest.getRoomsId().stream()
                .map(roomId -> roomDao.getById(roomId))
                .collect(Collectors.toList());

        facility.setRooms(rooms);

        for (Room room : rooms) {
            room.getFacilities().add(facility);
        }
    }
}
