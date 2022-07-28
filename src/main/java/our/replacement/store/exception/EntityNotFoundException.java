package our.replacement.store.exception;

public class EntityNotFoundException extends RuntimeException{
    String entityId;
    String classname;

    public EntityNotFoundException(String entityId, String className, String message){
        super(message);
        this.entityId=entityId;
        this.classname=className;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getClassname() {
        return classname;
    }
}
