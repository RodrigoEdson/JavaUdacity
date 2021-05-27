package udacity.java.sample.api.service;

public class DogNotFoundException extends RuntimeException{
    public DogNotFoundException() {
    }

    public DogNotFoundException(String message) {
        super(message);
    }
}
