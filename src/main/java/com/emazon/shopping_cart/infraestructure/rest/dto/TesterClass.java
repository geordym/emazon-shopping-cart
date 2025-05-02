package com.emazon.shopping_cart.infraestructure.rest.dto;


public class TesterClass {

    private String uuid;
    private String message;
    private Boolean success;

    public TesterClass() {
    }

    public TesterClass(String uuid, String message, Boolean success) {
        this.uuid = uuid;
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "TesterClass{" +
                "uuid='" + uuid + '\'' +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
