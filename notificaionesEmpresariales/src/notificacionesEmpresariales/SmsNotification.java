package notificacionesEmpresariales;

import java.util.Objects;
import java.util.regex.Pattern;

/*
    Notificación enviada por mensaje SMS.
    El uso de records garantiza la inmutabilidad de sus componentes.

    phoneNumber Número telefónico del destinatario (debe ser de 10 dígitos).
    message     Mensaje de texto a enviar.
*/
public record SmsNotification(String phoneNumber, String message) implements Notification {

    // Se compila estáticamente el patrón para evitar recompilaciones innecesarias
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    /*
        Constructor compacto que valida los parámetros requeridos para el SMS.

        NullPointerException si alguno de los parámetros es nulo.
        InvalidNotificationDataException si el número telefónico no posee exactamente 10 dígitos numéricos o si el mensaje está vacío.

    */
    public SmsNotification {
        Objects.requireNonNull(phoneNumber, "El número telefónico no puede ser nulo");
        Objects.requireNonNull(message, "El mensaje no puede ser nulo");

        if (!isValidPhone(phoneNumber)) {
            throw new InvalidNotificationDataException(
                    "El número telefónico debe contener exactamente 10 dígitos numéricos: "
                            + phoneNumber);
        }
        if (message.isBlank()) {
            throw new InvalidNotificationDataException("El mensaje no puede estar vacío");
        }
    }

    private static boolean isValidPhone(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches(); // Valida que el número tenga exactamente 10 dígitos
    }
}