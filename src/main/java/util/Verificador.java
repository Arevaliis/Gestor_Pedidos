package util;

import exception.ServiceException;

public class Verificador {

    public static String verificarPalabra(String palabra) throws ServiceException {

        if (palabra.isEmpty()){ throw new ServiceException("Debe ingresar un nombre. No puede dejar el campo vacío."); }
        if (!palabra.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ServiceException("El nombre ingresado no es válido. Solo puede contener letras."); }

        return palabra;
    }

    public static int verificarNumeroIngresado(String opc) {

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (opc.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("La opción ingresada debe ser un número entero válido."); }

        return Integer.parseInt(opc);
    }

    public static double verificarDecimalIngresado(String opc) {

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (!opc.matches("-?\\d+(\\.\\d+)?")) { throw new NumberFormatException("La opción ingresada debe ser un número decimal válido.");}

        return Double.parseDouble(opc);
    }
}