package util;

import exception.ServiceException;

public class Verificador {

    public static String verificarPalabra(String palabra) throws ServiceException {

        if (palabra.isEmpty()){ throw new ServiceException("Debe ingresar un nombre. No puede dejar el campo vacío."); }
        if (palabra.length() < 3){ throw new ServiceException("Debe tener al menos 3 caracteres"); }
        if (!palabra.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ServiceException("El nombre ingresado no es válido. Solo puede contener letras."); }

        return palabra;
    }

    public static int verificarNumeroIngresado(String opc) {

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (Integer.parseInt(opc) <= 0) { throw new NumberFormatException("Debe ingresar un valor mayor que 0"); }
        if (opc.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("La opción ingresada debe ser un número entero válido."); }

        return Integer.parseInt(opc);
    }

    public static double verificarDecimalIngresado(String opc) {

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (Double.parseDouble(opc) <= 0.00) { throw new NumberFormatException("Debe ingresar un valor mayor que 0.00"); }
        if (!opc.matches("-?\\d+(\\.\\d+)?")) { throw new NumberFormatException("La opción ingresada debe ser un número decimal válido.");}

        return Double.parseDouble(opc);
    }

    public static String verificarEmail(String email) throws ServiceException {

        if (email.isEmpty()){throw new ServiceException("Debe ingresar un email. No puede dejar el campo vacío.");}
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){throw new ServiceException("El formato del email no es correcto.");}

        return email;
    }
}