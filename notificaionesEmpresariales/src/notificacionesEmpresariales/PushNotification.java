package notificacionesEmpresariales;

import java.util.Objects;

/*
    Notificación Push enviada a un dispositivo específico.
    El uso de records garantiza la inmutabilidad de sus componentes.

    deviceToken Token identificador único del dispositivo móvil.
    message     Mensaje o alerta de la notificación.
*/
public record PushNotification(String deviceToken, String message)
        implements Notification {

    /*
        Constructor compacto que asegura la integridad de los datos de la notificación Push.

        NullPointerException si alguno de los parámetros esenciales es nulo.
        InvalidNotificationDataException si el token del dispositivo o el mensaje se encuentran vacíos o compuestos solo por espacios.
    */
    public PushNotification {
        Objects.requireNonNull(deviceToken, "El token no puede ser nulo");
        Objects.requireNonNull(message, "El mensaje no puede ser nulo");

        if (deviceToken.isBlank()) {
            throw new InvalidNotificationDataException("El token no puede estar vacío");
        }
        if (message.isBlank()) {
            throw new InvalidNotificationDataException("El mensaje no puede estar vacío");
        }
    }
}