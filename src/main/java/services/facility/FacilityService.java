package services.facility;

import dao.FacilityDao;
import dao.RoomDao;
import model.Facility;
import model.Room;
import request.FacilityRequest;

import java.util.List;
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

        facilityDao.saveFacility(facility);
    }

    public void updateFacility(Long id, FacilityRequest facilityRequest) {
        Facility facility = facilityDao.getFacility(id);
        upsertFacility(facility, facilityRequest);

        facilityDao.updateFacility(facility);
    }

    public void deleteFacility(Long id) {
        Facility facility = facilityDao.getFacility(id);
        facilityDao.deleteFacility(facility);
    }

    private void upsertFacility(Facility facility, FacilityRequest facilityRequest) {
        facility.setDescription(facilityRequest.getDescription());
        facility.setName(facilityRequest.getName());

        List<Room> rooms = facilityRequest.getRoomsId().stream()
                .map(roomId -> roomDao.getRoom(roomId))
                .collect(Collectors.toList());
    }
}
