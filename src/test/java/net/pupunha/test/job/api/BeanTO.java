package net.pupunha.test.job.api;

import net.pupunha.test.job.api.binding.EnumDataBinding;
import net.pupunha.test.job.api.binding.RestDataBinding;
import net.pupunha.test.job.api.fields.CheckBox;
import net.pupunha.test.job.api.fields.ComboBox;
import net.pupunha.test.job.api.fields.DatePicker;
import net.pupunha.test.job.api.fields.InputText;
import net.pupunha.test.job.api.validation.DateValidation;

import java.util.Date;

@DateValidation(initialDate = "dataInicial", finalDate = "dataFinal")
public class BeanTO {

    @InputText(required = true)
    private String caixaTexto;

    @CheckBox(required = true)
    @EnumDataBinding(MovementType.class)
    private String[] valueCheck;

    @ComboBox
    @EnumDataBinding(MovementType.class)
    private String movementCombo;

    @ComboBox
    @RestDataBinding(request = "http://localhost:8080/test/services/get_result")
    private String combo2;

    @ComboBox
    @Depends({"combo2", "caixaTexto"})
    @RestDataBinding(request = "http://localhost:8080/test/services/get_result/{combo2}")
    private String combo3;

    @DatePicker
    private Date dataInicial;

    @DatePicker
    private Date dataFinal;

}
