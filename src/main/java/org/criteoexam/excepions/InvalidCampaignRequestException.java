package org.criteoexam.excepions;

public class InvalidCampaignRequestException extends RuntimeException {

    private final String message;

    public InvalidCampaignRequestException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public InvalidCampaignRequestException(String msg, Exception e) {
        super(e);
        this.message = msg;
    }

    public InvalidCampaignRequestException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
