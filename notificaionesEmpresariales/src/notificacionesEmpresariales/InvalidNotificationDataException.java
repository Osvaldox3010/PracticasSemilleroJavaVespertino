package notificacionesEmpresariales;

// Excepción para indicar que los datos de una notificación no cumplen con las reglas de negocio o validaciones del sistema.

public class InvalidNotificationDataException extends RuntimeException {
    
    /*
        Construye una nueva excepción con el mensaje de detalle especificado.
    
        message El mensaje que describe el error de validación.
    */
    public InvalidNotificationDataException(String message) {
        super(message);
    }
}