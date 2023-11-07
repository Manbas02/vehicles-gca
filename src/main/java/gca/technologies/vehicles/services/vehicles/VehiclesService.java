package gca.technologies.vehicles.services.vehicles;

import gca.technologies.vehicles.model.Response;
import gca.technologies.vehicles.model.dto.GCARentDto;

public interface VehiclesService {

    Response rent(GCARentDto request);

    Response listRentals();

    Response recoverRent(Integer registerId);

    Response updateRentalStatus(GCARentDto request);

}
