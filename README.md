# PSPKey

## Descripción
PSPKey es una aplicación desarrollada en Java para la gestión de servicios mediante comunicación UDP. El proyecto incluye un modelo cliente-servidor diseñado para el intercambio eficiente de mensajes.

## Características principales
- **Arquitectura Cliente-Servidor:** Comunicación basada en sockets UDP.
- **Persistencia de datos:** Integración con bases de datos MySQL para el registro de información.
- **Modularidad:** Estructura organizada en controladores, modelos y vistas.

## Tecnologías utilizadas
- **Lenguaje:** Java
- **Base de Datos:** MySQL (conector incluido: `mysql-connector-java`)
- **Comunicación:** Protocolo UDP

## Instalación y Configuración

1. **Requisitos previos:**
   - Tener instalado Java (JDK 8 o superior).
   - Tener instalado un servidor MySQL.

2. **Base de Datos:**
   - Crea la base de datos necesaria en tu servidor MySQL.
   - Importa el script SQL (si tienes uno) para generar las tablas requeridas.

3. **Configuración del proyecto:**
   - Asegúrate de tener el driver `mysql-connector-java-5.1.38-bin.jar` en tu *classpath* o carpeta `lib`.
   - Modifica los parámetros de conexión a la base de datos en las clases correspondientes (ubicadas en `PSPKey/bin/modelo/`).

4. **Ejecución:**
   - Compila el proyecto desde la carpeta raíz.
   - Ejecuta `PrincipalServidor` primero para iniciar el servicio de escucha.
   - Ejecuta `PrincipalCliente` para realizar la conexión.

## Estructura del Proyecto
- `bin/controlador/`: Contiene la lógica principal de conexión.
- `bin/modelo/`: Contiene las entidades y la lógica de base de datos.
- `bin/vista/`: Contiene la interfaz o consola para el usuario.
- `lib/`: Librerías externas (JDBC Connector).

## Contacto
Desarrollado por José Alejandro Pampa Taguada.
