package xyz.mattyb.checkmate;

public class InstanceCheck {

    private final Object object;
    private Class<?> clazz;

    public InstanceCheck(final Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
