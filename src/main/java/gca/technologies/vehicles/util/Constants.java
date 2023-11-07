package gca.technologies.vehicles.util;

public enum Constants {

    OK(0, "La operación se ha realizado correctamente"),

    OK_NO_DATA(1, "La operación se ha realizado correctamente pero no se han encontrado datos"),

    ERROR(-1, "Se ha producido un error"),

    ERROR_NOT_FOUND(-2, "No se ha encontrado el elemento solicitado"),

    ERROR_VALIDATION(-3, "Se ha producido un error de validación");

    private final Integer responseCode;

    private final String message;

    Constants(Integer responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }
}
