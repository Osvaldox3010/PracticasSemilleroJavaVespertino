# - Sistema de Notificaciones Empresariales

Esta practica consiste en un sistema robusto y extensible para la gestión y simulación del envío de diferentes tipos de notificaciones empresariales (Correo Electrónico, SMS y Mensajes Push). El objetivo principal de la práctica es aplicar características avanzadas e introducidas en las versiones modernas de Java (Java 21), garantizando la inmutabilidad de los datos, la integridad del negocio mediante validaciones estrictas y un flujo de control limpio sin estructuras condicionales redundantes.

## -> Características Principales

* **Interfaces Selladas (`sealed interface`)**: Se utiliza `Notification` como una interfaz sellada para controlar explícitamente qué clases pueden heredar de ella (`EmailNotification`, `SmsNotification`, `PushNotification`), permitiendo un control absoluto sobre el dominio del sistema.
* **Registros (`records`)**: Las notificaciones están implementadas como componentes inmutables por definición, reduciendo el código repetitivo (*boilerplate*) al generar automáticamente los constructores, métodos de acceso, `equals()`, `hashCode()` y `toString()`.
* **Constructores Compactos**: Cada tipo de notificación valida sus propios parámetros antes de su instanciación en un bloque compacto, garantizando que no existan objetos en un estado inválido o corrupto dentro de la memoria de la aplicación.
* **Coincidencia de Patrones para switch (`Pattern Matching for switch`)**: El procesamiento y conteo estadístico del arreglo de notificaciones se realiza mediante un `switch` exhaustivo moderno, eliminando por completo el uso del operador tradicional `instanceof` y bloques `if-else` encadenados.
* **Manejo Diferido de Excepciones mediante Lambdas (`Runnable`)**: Para demostrar el control de errores personalizados sin interrumpir abruptamente la carga inicial de datos válidos, se diseñó un arreglo de acciones ejecutables (`Runnable[]`) que inyecta casos erróneos y evalúa las excepciones de forma aleatoria al finalizar el resumen del sistema.

---

## -> Estructura de la práctica

El entorno de desarrollo está organizado bajo la estructura estándar de paquetes de Java:

```text
NOTIFICACIONES-EMPRESARIALES/
├── .vscode/        # Configuraciones del entorno en VS Code
├── bin/            # Archivos binarios compilados (.class)
├── lib/            
├── src/            # Código fuente principal de la aplicación
│   └── notificacionesEmpresariales/
│       ├── EmailNotification.java
│       ├── InvalidNotificationDataException.java
│       ├── Main.java
│       ├── Notification.java
│       ├── PushNotification.java
│       └── SmsNotification.java
└── test/

```

---

## -> Reglas de Validación de Negocio

El sistema rechaza la creación de cualquier objeto que no cumpla con los siguientes criterios estructurales:

1. **General**: Ningún parámetro de entrada puede ser nulo o estar compuesto únicamente por espacios en blanco.
2. **EmailNotification**: La dirección de correo electrónico debe cumplir estrictamente con una expresión regular estándar de formato (`usuario@dominio.com`).
3. **SmsNotification**: El número telefónico debe estar compuesto de manera exacta por un formato numérico de 10 dígitos (`\d{10}`).
4. **PushNotification**: El token del dispositivo receptor debe ser una cadena con caracteres válidos y no vacía.

Cualquier violación a estas reglas de negocio deberá detener la construcción del objeto y disparará una excepción personalizada de tipo `InvalidNotificationDataException`.

---

## -> Ejemplo de Salida en Consola

Al ejecutar la aplicación, se despliega la simulación completa de los registros exitosos, seguida del resumen cuantitativo y la ejecución aleatoria de un caso erróneo controlado:

```text
[EMAIL] Para: juan.perez@empresa.com | Asunto: Bienvenida
[EMAIL] Para: ana.lopez@empresa.com | Asunto: Factura disponible
[SMS]   Para: 5512345678 | Mensaje: Tu código de verificación es 4321
[PUSH]  Token: abc123xyz987 | Mensaje: Tienes un nuevo mensaje
[EMAIL] Para: carlos.ruiz@empresa.com | Asunto: Promoción especial
[SMS]   Para: 5523456789 | Mensaje: Tu pedido ha sido enviado
[PUSH]  Token: tok456def321 | Mensaje: Recordatorio de reunión a las 3pm
[PUSH]  Token: push789ghi654 | Mensaje: Actualización de la app disponible
[SMS]   Para: 5534567890 | Mensaje: Confirmación de pago recibido
[EMAIL] Para: laura.gomez@empresa.com | Asunto: Soporte técnico
[PUSH]  Token: notify001aaa | Mensaje: Nueva oferta especial para ti
[PUSH]  Token: notify002bbb | Mensaje: Tu suscripción vence pronto

---------- Notificaciones Enviadas ----------
Correos enviados: 4
SMS enviados: 3
Push enviados: 5
Total: 12

---------- Excepción ----------
Se ejecutó el caso de prueba erróneo #3
Excepción capturada de forma segura: InvalidNotificationDataException
Mensaje de error del sistema:       El número telefónico debe contener exactamente 10 dígitos numéricos: 55123

```