package net.pupunha.test.job.api.binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@DataBinding(DataBindingType.ENUM)
public @interface EnumDataBinding {

    Class value();

}
