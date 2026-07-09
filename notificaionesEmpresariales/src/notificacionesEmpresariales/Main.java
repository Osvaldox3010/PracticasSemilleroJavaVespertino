package notificacionesEmpresariales;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int correosEnviados = 0;
        int smsEnviados = 0;
        int pushEnviados = 0;

        Notification[] notifications = new Notification[]{
                new EmailNotification("juan.perez@empresa.com", "Bienvenida",
                        "Gracias por registrarte en nuestro sistema."),
                new EmailNotification("ana.lopez@empresa.com", "Factura disponible",
                        "Tu factura del mes ya está disponible."),
                new SmsNotification("5512345678", "Tu código de verificación es 4321"),
                new PushNotification("abc123xyz987", "Tienes un nuevo mensaje"),
                new EmailNotification("carlos.ruiz@empresa.com", "Promoción especial",
                        "Aprovecha 20% de descuento hoy."),
                new SmsNotification("5523456789", "Tu pedido ha sido enviado"),
                new PushNotification("tok456def321", "Recordatorio de reunión a las 3pm"),
                new PushNotification("push789ghi654", "Actualización de la app disponible"),
                new SmsNotification("5534567890", "Confirmación de pago recibido"),
                new EmailNotification("laura.gomez@empresa.com", "Soporte técnico",
                        "Hemos resuelto tu ticket exitosamente."),
                new PushNotification("notify001aaa", "Nueva oferta especial para ti"),
                new PushNotification("notify002bbb", "Tu suscripción vence pronto")
        };


        for (Notification notification : notifications) {

            // Switch moderno con pattern matching
            String detalle = switch (notification) {
                case EmailNotification email -> {
                    correosEnviados++;
                    yield "[EMAIL] Para: %s | Asunto: %s".formatted(email.email(), 
                                                                    email.subject());
                     }
                case SmsNotification sms -> {
                    smsEnviados++;
                    yield "[SMS]   Para: %s | Mensaje: %s".formatted(sms.phoneNumber(), 
                                                                     sms.message());
                     }
                case PushNotification push -> {
                    pushEnviados++;
                    yield "[PUSH]  Token: %s | Mensaje: %s".formatted(push.deviceToken(), 
                                                                      push.message());
                    }
            };

            System.out.println(detalle);
        }

        int total = correosEnviados + smsEnviados + pushEnviados;

        System.out.println();
        System.out.println("\n\t---------- Notificaciones Enviadas ----------");
        System.out.println("\tCorreos enviados: " + correosEnviados);
        System.out.println("\tSMS enviados: " + smsEnviados);
        System.out.println("\tPush enviados: " + pushEnviados);
        System.out.println("\tTotal: " + total);

        System.out.println("\n\t---------- Excepcion ----------");

        Runnable[] invalidTriesRunnables = new Runnable[]{
            () -> new EmailNotification("correo-sin-formato.com", "Asunto", "Contenido"),
            () -> new EmailNotification("usuario@empresa.com", "   ", "Contenido vacío"),
            () -> new SmsNotification("55123", "Teléfono muy corto"),
            () -> new PushNotification("   ", "Token push en blanco")
        };

        // Caso elegido aleatoriamente para forzar la excepción
        Random random = new Random();
        int indexRandom = random.nextInt(invalidTriesRunnables.length);
        Runnable choosenCase = invalidTriesRunnables[indexRandom];

        try {
                // Se ejecuta el caso de prueba que debería lanzar la excepción
                choosenCase.run();
        } catch (InvalidNotificationDataException e) {
            System.out.println("Se ejecutó el caso de prueba erróneo #" + (indexRandom + 1));
            System.out.println("Excepción capturada de forma segura: " + e.getClass().getSimpleName());
            System.out.println("Mensaje de error del sistema:       " + e.getMessage());
        }

    }
}
