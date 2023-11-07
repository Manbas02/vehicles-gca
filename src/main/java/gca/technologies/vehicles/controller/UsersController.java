package gca.technologies.vehicles.controller;

import gca.technologies.vehicles.model.Response;
import gca.technologies.vehicles.model.dto.GCAUserPaymentDto;
import gca.technologies.vehicles.model.dto.GCAUsersDto;
import gca.technologies.vehicles.services.users.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Conjunto de operaciones para el manejo de usuarios
 */
@Slf4j
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(value = "/recuperar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getUserById(@RequestParam(name = "idRegistroUsuario") Integer registerId) {
        return usersService.getUserById(registerId);
    }

    @PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addUser(@RequestBody GCAUsersDto request) {
        return usersService.addUser(request);
    }

    @DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUser(@RequestParam(name = "idRegistroUsuario") Integer registerId) {
        return usersService.deleteUserById(registerId);
    }

    @PostMapping(value = "/agregarMedioDePago", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addPaymentMethod(@Validated @RequestBody GCAUserPaymentDto request) {
        return usersService.addPaymentMethod(request);
    }

    @PutMapping(value = "/editarMedioPago", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateUserPaymentMethod(@RequestBody GCAUserPaymentDto request) {
        return usersService.updateUserPaymentMethod(request);
    }

    @DeleteMapping(value = "/eliminarMedioPago", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUserPaymentMethod(@RequestBody GCAUserPaymentDto userPaymentDto) {
        return usersService.deleteUserPaymentMethod(userPaymentDto);
    }

}
