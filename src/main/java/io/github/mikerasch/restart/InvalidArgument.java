package io.github.mikerasch.restart;

public final class InvalidArgument extends RuntimeException {
    private InvalidArgument(String message) {
        super(message);
    }

    public static InvalidArgument empty() {
        return new InvalidArgument("No arguments specified.");
    }

    public static InvalidArgument missing(String keyName) {
        return new InvalidArgument("Missing argument %s".formatted(keyName));
    }

    public static InvalidArgument valueError(String keyName) {
        return new InvalidArgument("Invalid value for argument: " + keyName);
    }
}
