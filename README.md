# Account and Movement Manager API

A continuación se detalla la arquitectura de la aplicación

## Proceso de Construcción

Se utilizaron las siguientes herramientas:

 - SpringBoot con apache por default
 - JpaRepository para manejo de operaciones a la BDD.
 - Arquitectura Hexagonal apoyado por DDD dividiendo la aplicación en Domain, Infraestructure y Application.
 - Uso del patrón de comportamiento **Mediator** para delegar la ejecución de los **Commands** desacoplando las capas.
 - Autenticación a través de usuario y contraseña con un endpoint dedicado.
 - **JWT** para autorización a cada endpoint.
 - Seguridad con SpringSecurity reforzada con Roles **ROLE_ADMIN** y **ROLE_USER** 

## Proceso de ejecución
### Prerequisitos

 - Tener instalado Docker (docker compose)

### Instalación y Ejecución
 1. Clonar el repositorio.
 2. Abrir un terminal en la raíz del proyecto y ejecutar el comando `docker-compose up -d`

## Accesos

El proyecto tiene por defecto un usuario Administrador con las siguientes credenciales
Identification: 1715849731
Password: 1715849731
El endpoint de autenticación es: http://localhost:8001/api/auth/login el cual devolverá un access_token (válido por 5 minutos, configurable desde application.yml) para enviarlo como Authorization de tipo Bearer en el Header para el resto de requests.

Para añadir un cliente se debe usar el endpoint /api/admin/client el cual creará un cliente y un usuario (ROLE_USER) para el mismo con su identificación como usuario y contraseña asignados. 
Cada cliente es reconocido en el sistema con su "identification".

## Documentación API
Una vez ejecutada la aplicación, se encontrará disponible un **Swagger** en la ruta http://localhost:8001/swagger-ui.html#/
A su vez el documento **AccountManagerAPI.postman_collection.json** contiene una colección de ejemplo de las llamadas a cada endpoint para Postman. 
Las llamadas marcadas como [ADMIN] solo reciben un token de Autorización de un usuario ADMIN, mientras que las [USER] solo reciben tokens de Clientes (Tendrá que loggearse primero para obtener el access_token). 
