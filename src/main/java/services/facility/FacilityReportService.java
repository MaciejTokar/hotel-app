package services.facility;

import dao.FacilityDao;
import mapping.FacilityMapper;

public class FacilityReportService {

    private FacilityDao facilityDao;
    private FacilityMapper facilityMapper;

    public FacilityReportService(FacilityDao facilityDao, FacilityMapper facilityMapper) {
        this.facilityDao = facilityDao;
        this.facilityMapper = facilityMapper;
    }
}
