package annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.LOCAL_VARIABLE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TabelaAnnotation {
    String nome();
}
