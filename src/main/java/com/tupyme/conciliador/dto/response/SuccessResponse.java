package com.tupyme.conciliador.dto.response;

public class SuccessResponse {

    private String message; // Mensaje de éxito

    public SuccessResponse(String message) {
        this.message = message;
    }

    // Getter y Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
