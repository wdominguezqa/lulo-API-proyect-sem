package com.lulobank.exceptions;

public class ErrorAssertions extends AssertionError {

    public static final String CODES_DO_NOT_MATCH = "El estado recibido al consultar el servicio no es el esperado";
    public static final String INVALID_DATA = "La información obtenida no corresponde a la consultada";
    public static final String INVALID_STATUS_RECORD  = "El estado del registro consultado no es el esperado";
}

