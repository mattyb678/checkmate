package xyz.mattyb.checkmate.checker;

import xyz.mattyb.checkmate.InstanceCheck;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

import static java.lang.String.format;

public class TypeCheckers {

    private TypeCheckers() {
        // empty on purpose
    }

    public static Checker<InstanceCheck> instanceOf = new Checker<InstanceCheck>() {
        @Override
        public boolean test(InstanceCheck object, CheckerContext ctx) {
            if (object == null || object.getObject() == null || object.getClazz() == null) {
                ctx.setNpe(true);
                return true;
            }
            return !(object.getClazz().isInstance(object.getObject()));
        }

        @Override
        public String getExceptionMessage(InstanceCheck object, CheckerContext ctx) {
            if (object == null || object.getObject() == null || object.getClazz() == null) {
                return "Object is not the expected type";
            }
            return format("Expected type: %s, actual type: %s", object.getClazz(), object.getObject().getClass());
        }
    };
}
