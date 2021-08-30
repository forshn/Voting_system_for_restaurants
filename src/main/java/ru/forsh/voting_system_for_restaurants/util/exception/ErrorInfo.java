package ru.forsh.voting_system_for_restaurants.util.exception;

public class ErrorInfo {
    private final String url;
    private final ru.forsh.voting_system_for_restaurants.util.exception.ErrorInfo type;
    private final String typeMessage;
    private final String[] details;

    public ErrorInfo(CharSequence url, ru.forsh.voting_system_for_restaurants.util.exception.ErrorInfo type, String typeMessage, String... details) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = typeMessage;
        this.details = details;
    }
}
