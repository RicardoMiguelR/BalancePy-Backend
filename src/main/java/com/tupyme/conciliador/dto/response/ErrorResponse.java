package com.tupyme.conciliador.dto.response;

public class ErrorResponse {

    private String error; // Mensaje de error

    public ErrorResponse(String error) {
        this.error = error;
    }

    // Getter y Setter para errores
    public String getError() {
        return error;
    }

    public void setError() {
        this.error = error;
    }
}
