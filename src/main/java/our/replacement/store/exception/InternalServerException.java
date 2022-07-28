package our.replacement.store.exception;

public class InternalServerException extends RuntimeException {

    private final String className;

    public InternalServerException(String className) {
        super();
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}