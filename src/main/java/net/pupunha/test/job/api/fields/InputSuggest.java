package net.pupunha.test.job.api.fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@FormField
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InputSuggest {

    boolean required() default false;

}
