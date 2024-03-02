package services.facility;

import dao.FacilityDao;
import dao.RoomDao;
import exeption.BaseException;
import exeption.ErrorCode;
import mapping.FacilityMapper;
import model.Facility;
import model.Room;
import request.FacilityRequest;
import response.FacilityResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FacilityService {
    private FacilityDao facilityDao;
    private RoomDao roomDao;
    private FacilityMapper facilityMapper;

    public FacilityService(FacilityDao facilityDao, RoomDao roomDao, FacilityMapper facilityMapper) {
        this.facilityDao = facilityDao;
        this.roomDao = roomDao;
        this.facilityMapper = facilityMapper;
    }

    public FacilityResponse saveFacility(FacilityRequest facilityRequest) {
        Facility facility = new Facility();
        upsertFacility(facility, facilityRequest);

        facilityDao.save(facility);

        return facilityMapper.fromFacilityToFacilityResponse(facilityDao.getById(facility.getId()));
    }

    public FacilityResponse updateFacility(Long id, FacilityRequest facilityRequest) {
        Facility facility = facilityDao.getById(id);
        upsertFacility(facility, facilityRequest);

        facilityDao.update(facility);

        return facilityMapper.fromFacilityToFacilityResponse(facilityDao.getById(facility.getId()));
    }

    public void deleteFacility(Long facilityId) {
        Facility facility = facilityDao.getById(facilityId);
        Optional.ofNullable(facility)
                .map(Facility::getRooms)
                .ifPresent(rooms -> {
                    if (!rooms.isEmpty()) {
                        throw new BaseException(ErrorCode.FACILITY_ROOM_EXISTENCE, String.valueOf(facilityId));
                    }
                });

        facilityDao.delete(facility);
    }

    public void deleteFacilityOfRoom(Long facilityId, Long roomId) {
        Facility facility = facilityDao.getById(facilityId);

        if (facility == null) {
            throw new BaseException(ErrorCode.FACILITY_ID_EXCEPTION, String.valueOf(facilityId));
        }

        facility.getRooms().stream()
                .filter(room -> Objects.equals(room.getId(), roomId))
                .findAny()
                .orElseThrow(() -> new BaseException(ErrorCode.FACILITY_ROOM_EXISTENCE, String.valueOf(facilityId), String.valueOf(roomId)));

        facilityDao.deleteFacilityOfRoom(facilityId, roomId);
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
