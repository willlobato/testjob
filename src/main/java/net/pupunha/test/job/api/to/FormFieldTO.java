package net.pupunha.test.job.api.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.lang.annotation.Annotation;

@AllArgsConstructor
public class FormFieldTO {

    @Getter
    private Annotation annotationField;

    @Getter
    private Boolean hasDataBinding;

}
