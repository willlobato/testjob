package net.pupunha.test.job.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.pupunha.test.job.api.binding.DataBinding;
import net.pupunha.test.job.api.binding.EnumDataBinding;
import net.pupunha.test.job.api.binding.RestDataBinding;
import net.pupunha.test.job.api.fields.FormField;
import net.pupunha.test.job.api.to.DataBindingTO;
import net.pupunha.test.job.api.to.FormFieldTO;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Map<String, Object>> json = new ArrayList<>();

        Field[] declaredFields = BeanTO.class.getDeclaredFields();
        for (Field field : declaredFields) {
            Map<String, Object> jsonField = new LinkedHashMap<>();
            jsonField.put("name", field.getName());

            fillFormField(declaredFields, field, jsonField);

            json.add(jsonField);
        }

        try {
            String s = mapper.writeValueAsString(json);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public static void fillFormField(Field[] declaredFields, Field field, Map<String, Object> jsonField) {

        FormFieldTO formField = getFormField(field);
        String type;
        if (formField != null) {
            type = formField.getAnnotationField().annotationType().getSimpleName();
            jsonField.put("type", type);
            Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(formField.getAnnotationField());
            jsonField.put("attributes", attributes);
        } else {
            throw new RuntimeException("Doesn't have any form field annotation.");
        }

        String[] depends = getDepends(field);
        if (depends != null) {
            List<String> fields = Arrays.stream(declaredFields)
                    .map(Field::getName)
                    .collect(Collectors.toList());
            for (String dependsField : depends) {
                if (!fields.contains(dependsField)) {
                    throw new RuntimeException("Field ["+dependsField+"] not exist on @Depends.");
                }
            }

            jsonField.put("depends", depends);
        }

        if (formField.getHasDataBinding()) {
            DataBindingTO dataBinding = getDataBinding(field);
            if (dataBinding != null) {
                Map<String, Object> dataBindingMap = new LinkedHashMap<>();
                dataBindingMap.put("type", dataBinding.getType());
                switch (dataBinding.getType()) {
                    case ENUM:
                        EnumDataBinding enumDataBinding  = (EnumDataBinding) dataBinding.getAnnotationDataBinding();
                        Object[] enumConstants = enumDataBinding.value().getEnumConstants();
                        dataBindingMap.put("values", enumConstants);
                        break;
                    case REST:
                        RestDataBinding restDataBinding = (RestDataBinding) dataBinding.getAnnotationDataBinding();
                        dataBindingMap.put("request", restDataBinding.request());
                        break;
                }
                jsonField.put("dataBinding", dataBindingMap);
            } else {
                throw new RuntimeException("This form field "+type+" require data binding.");
            }
        }

    }

    public static FormFieldTO getFormField(Field field) {
        for (Annotation annotationField : field.getAnnotations()) {
            FormField formField = annotationField.annotationType().getAnnotation(FormField.class);
            if (formField != null) {
                return new FormFieldTO(annotationField, formField.hasDataBinding());
            }
        }
        return null;
    }

    public static DataBindingTO getDataBinding(Field field) {
        for (Annotation annotationField : field.getAnnotations()) {
            DataBinding dataBinding = annotationField.annotationType().getAnnotation(DataBinding.class);
            if (dataBinding != null) {
                return new DataBindingTO(annotationField, dataBinding.value());
            }
        }
        return null;
    }

    public static String[] getDepends(Field field) {
        Depends depends = field.getAnnotation(Depends.class);
        if (depends != null) {
            return depends.value();
        }
        return null;
    }

}
