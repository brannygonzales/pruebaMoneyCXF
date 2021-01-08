package com.avantica.serviciosparte2.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class Util {

    private static final String COMA = ",";
    private static final String ESPACIO = " ";
    private static final String FORMATO_ID_TRANSACCION = "yyyyMMddHHmmssSSS";
    private static final String VACIO = "";
    private static final String DOS_PUNTOS = ":";
    private static final String SALTO_LINEA = "\n";

    private Util() {
    }

    public static DateFormat getLocalFormat() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", new Locale("es", "PE"));
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat;
    }
    
    public static String printPrettyJSONString(Object o) {
        try {
            return new ObjectMapper().setDateFormat(getLocalFormat())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> Set<ConstraintViolation<T>> validarCampos(T objeto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(objeto);
    }

    public static <T> String validarCamposObligatorios(T objeto) {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validarCampos(objeto);
        StringBuilder sbCampos = new StringBuilder();
        int contador = 0;
        for (ConstraintViolation<T> violation : violations) {
            sbCampos.append(violation.getPropertyPath().toString());
            contador++;
            if (contador < violations.size()) {
                sbCampos.append(COMA + ESPACIO);
            }
        }
        return sbCampos.toString();
    }
    
    public static <T> String obtenerCamposIncorrectos(T objeto) {
        Set<ConstraintViolation<T>> violations = validarCampos(objeto);
        StringBuilder sbCampos = new StringBuilder();
        int contador = 0;
        for (ConstraintViolation<T> violation : violations) {
            sbCampos.append(violation.getPropertyPath().toString());
            sbCampos.append(DOS_PUNTOS + ESPACIO);
            sbCampos.append(violation.getMessage());
            contador++;
            if (contador < violations.size()) {
                sbCampos.append(SALTO_LINEA);
            }
        }
        return sbCampos.toString();
    }
    
    public static String formatearFecha(Date fecha, String formato) {
        String fechaFormateada = VACIO;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
            fechaFormateada = formatoFecha.format(fecha);
        } catch (Exception e) {
        }
        return fechaFormateada;
    }
    
    public static Date parsearFecha(String fecha, String formato) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
            formatoFecha.setLenient(false);
            return formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static String obtenerIdTransaccion() {
        return formatearFecha(new Date(), FORMATO_ID_TRANSACCION);
    }
    
    public static void validarFecha(Component componentePadre, JTextComponent jtxtValidacion, 
            JTextComponent jtxtSiguiente, String formatoFecha, String mensajeFechaInvalida) {
        Date parsedDate = Util.parsearFecha(jtxtValidacion.getText(), formatoFecha);
        if (parsedDate == null) {
            JOptionPane.showMessageDialog(componentePadre, mensajeFechaInvalida);
            jtxtValidacion.requestFocus();
        } else {
            jtxtSiguiente.requestFocus();
        }
    }

}
