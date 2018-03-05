package net.pupunha.test.job.api.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.pupunha.test.job.api.binding.DataBindingType;

import java.lang.annotation.Annotation;

@AllArgsConstructor
public class DataBindingTO {

    @Getter
    private Annotation annotationDataBinding;

    @Getter
    private DataBindingType type;

}
