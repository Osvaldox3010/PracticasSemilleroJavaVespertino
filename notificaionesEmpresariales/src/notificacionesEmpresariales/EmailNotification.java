package notificacionesEmpresariales;

import java.util.Objects;
import java.util.regex.Pattern;

/*
    Notificación enviada por correo electrónico.
    El uso de records garantiza la inmutabilidad de sus componentes.

    email   Dirección de correo electrónico del destinatario.
    subject Asunto del mensaje.
    content Contenido o cuerpo del mensaje.
*/
public record EmailNotification(String email, String subject, String content)
        implements Notification {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    /*
     Constructor compacto que valida de forma exhaustiva los componentes del correo.

     NullPointerException si alguno de los parámetros esenciales es nulo.
     InvalidNotificationDataException si el formato del correo no es válido o si el asunto o contenido se encuentran vacíos.
    */
    public EmailNotification {
        Objects.requireNonNull(email, "El correo electrónico no puede ser nulo");
        Objects.requireNonNull(subject, "El asunto no puede ser nulo");
        Objects.requireNonNull(content, "El contenido no puede ser nulo");

        if (!isValidEmail(email)) {
            throw new InvalidNotificationDataException(
                    "Correo electrónico inválido: " + email);
        }
        if (subject.isBlank()) {
            throw new InvalidNotificationDataException("El asunto no puede estar vacío");
        }
        if (content.isBlank()) {
            throw new InvalidNotificationDataException("El contenido no puede estar vacío");
        }
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}