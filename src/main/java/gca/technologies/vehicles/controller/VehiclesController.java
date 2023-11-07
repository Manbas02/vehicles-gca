package gca.technologies.vehicles.controller;

import gca.technologies.vehicles.model.Response;
import gca.technologies.vehicles.model.dto.GCARentDto;
import gca.technologies.vehicles.services.vehicles.VehiclesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rentaVehiculos")
@RequiredArgsConstructor
public class VehiclesController {

    private final VehiclesService vehiclesService;

    @PostMapping(value = "/rentar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response rent(@RequestBody GCARentDto request) {
        return vehiclesService.rent(request);
    }

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response listRentals() {
        return vehiclesService.listRentals();
    }

    @PostMapping(value = "/recuperar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response recoverRent(@RequestParam(name = "idRegistro") Integer registerId) {
        return vehiclesService.recoverRent(registerId);
    }

    @PutMapping(value = "/actualizarEstado", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateRentalStatus(@RequestBody GCARentDto request) {
        return vehiclesService.updateRentalStatus(request);
    }



}
