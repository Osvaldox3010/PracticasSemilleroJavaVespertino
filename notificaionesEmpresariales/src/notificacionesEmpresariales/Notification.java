package notificacionesEmpresariales;

/*
        Interfaz sellada que representa el tipo base de cualquier notificación del sistema.Email, SMS y Push son los tipos de notificación permitidos, garantizando así
        que no se puedan crear implementaciones externas no controladas.
 */
public sealed interface Notification  permits EmailNotification, SmsNotification, PushNotification {
}
