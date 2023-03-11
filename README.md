# Challenge WeConnect

Documentación correspondiente al reto propuesto por la empresa WeConnect para su proceso de reclutamiento.

## Autor

- [@guaichaAndres](https://github.com/guaichaAndres)

## Tech Stack

**Client:** Postman

**Server:** Spring Boot, PostgreSQL

El Server Backend utiliza las siguientes **Librerias:**

- Spring Dev Tools
- Lombok
- Spring Jpa
- Spring Data Validation
- Spring Fox
- Oauth
- Jwt

## Creación del modelo de datos

### Análisis

- Una de las soluciones al problema planteado es usar una lógica de roles. 
- Gracias a los roles, podremos discriminar entre usuarios administradores (con todos los privilegios que ello amerita) 
y usuarios empleados. Un usuario empleado tendrá acceso restringido a las APIS de administrador. 
- El usuario empleado será el encargado de suministrar al sistema la información sobre si está vacunado o no. Gracias a
esta información, se podrá determinar si ingresa a la lista de vacunación.
- Existen dos tipos de Roles: ['ROLE_ADMIN', 'ROLE_USER']



El esquema de datos se ubica en la carpeta `DataModel` del proyecto. 

La información del usuario propietario de la base de datos PostgreSqL es: 

```info
Database: Challenge
User: usuario_weconnect
password: weconnect456
```

Las tablas dentro de la BD son: 

![Tablas BD PostgreSqL](/images/tablasBD.png)


## Funcionamiento

Se implementó Swagger y su interfaz gráfica Spring Fox para la documentacion de la Api Rest. También, se creará un usuario 
default con provilegio de administrador (ROLE: ADMIN). Las credenciales de este usuario son: 
 
```info
**username**: aGuaicha

**password**: 0301815593
```
### Creacion de un Empleado

- Para fines de pruebas y test, se adjuntará el formato correcto de inserción de un usuario empleado. 
- Todos los datos se encuentran validados: no permitirá la inserción de caracteres no permitidos, correos iválidos o cédulas 
de menos de 10 dígitos. Esto se logró mediante el uso de expresiones regulares. 

```json
{
    "nombre": "Pedro",
	"apellido": "Ortega",
	"email": "pedrortega@gmail.com",
	"cedula": "1105808149"
}
```

**Nota:** El nombre de usuario (no confundir con nombre de persona) se creará tomando la primera letra del nombre y el apellido. El password del usuario creado es su cédula de identidad.
Para el ejemplo el usuario creado sería:

`user: POrtega pass: 1105808149`


### Actualizacion Empleado con Vacunas

```json
{
    "fechaNacimiento": "1986-10-02",
    "telefonoCelular": "0960851986",
    "direccion": "Calle Vieja y Yanahurco,
    "estadoVacunas": "Vacunado",
    "vacunas": [
        {
            "vaccineType": "Jhonson y Jhonson",
            "vaccineDate": "2021-10-23",
            "vaccineNumber": 2
        },
        {
            "vaccineType": "Pfizer",
            "vaccineDate": "2022-01-21",
            "vaccineNumber": 1
        },
        {
            "vaccineType": "Sinovac",
            "vaccineDate": "2022-08-19",
            "vaccineNumber": 1
        }
    ]
}
```

### Actualizacion Empleado Sin Vacunas

```json
{
    "fechaNacimiento": "1991-11-15",
    "telefonoCelular": "0960851984",
    "direccion": "Simon Bolivar y Padre Aguirre",
    "estadoVacunas": "No Vacunado"
}
```

Con el proyecto implementado a nivel local, se puede acceder a este enlace para visualizar la documentación de la APi
link: [Swagger](http://localhost:8080/swagger-ui.html#/)

![Documentacion](/images/springFox.png)

### Tabla de URLs Administrador
Función | URL
------------ | -------------
Login | localhost:8080/oauth/token
Creacion Empleado | localhost:8080/api/v1/employee
Borrar Empleado | localhost:8080/api/v1/employee/{id_usuario}
Lista por Tipo de Vacuna | localhost:8080/api/v1/admin/tipo
Lista por Rango de Fechas | localhost:8080/api/v1/admin/rango
Lista por Estado de Vacuna | localhost:8080/api/v1/admin/estado

### Tabla de URLs Empleado
Función | URL
------------ | -------------
Login | localhost:8080/oauth/token
Actualizar Empleado | localhost:8080/api/v1/employee/{id_usuario}


## Autenticacion

El servidor de autorizacion habilitado en el backend contiene un usuario guardado en memoria. Para generar el token de acceso para el resto de rutas, se debe ingresar al  `Login` descrito en las tablas de funcionamiento. En la parte de `Authorization` se validará el usuario insertado en memoria. 

**username**: weconnect

**password**: weconnect

![Authorization](/images/auth.png)

Si las credenciales son correctas nos dará el visto bueno

![Authorization](/images/ok.png)

Una vez autenticado, el servidor de autorización genera un token. 
Este Token será usado para autorizar todas las peticiones al resto de endopoints listados anteriormente. 

![Token](/images/token.png)

## Use

Si el proceso de autentificación fué existoso, se tendrá acceso a los endpoints del server. 
En este ejemplo, se creó un usuario con rol de empleado. 

![User](/images/crearUsuario.png)

Como se puede observar, el usuario ha sido generado automáticamente y el password correspondiente a la cédula fué encriptado gracias a ByCript, conservando de esa manera la seguridad de credenciales. 

## Artículos base consultados para el desarrollo de este proyecto

[SWAGGER Y SWAGGER UI: ¿QUÉ ES Y POR QUÉ ES IMPRESCINDIBLE PARA TUS APIS?](https://www.chakray.com/es/swagger-y-swagger-ui-por-que-es-imprescindible-para-tus-apis/)

[Authentication based in roles](https://www.devglan.com/spring-security/spring-oauth2-role-based-authorization)

[Introduction to Project Lombok](https://www.baeldung.com/intro-to-project-lombok)

## Manejo de errores. 

Como se mencionó anteriormente, los datos se encuentran validados de manera correcta. 
Para el mismo ejemplo de creación de usuario empleado, esta vez se omite la cédula y el servidor devuelve la excepción correspondiente, indicando que la cédula debe contener 10 dígitos. 

![Error](/images/Error.png)


