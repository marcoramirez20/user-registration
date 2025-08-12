# User Registration API (Spring Boot)

Prueba técnica: API RESTful de **creación de usuarios** con **Java + Spring Boot**, **JPA/H2**, validaciones configurables, **JWT** (opcional activado), manejo de errores `{"mensaje": "..."}`, **Swagger** y **pruebas**.

## Requisitos cumplidos
- Solo JSON en request/response y errores con formato `{ "mensaje": "..." }`.
- Endpoint `POST /api/v1/users` que recibe `name`, `email`, `password`, `phones[]`.
- Respuesta exitosa (201) incluye `id (UUID)`, `created`, `modified`, `last_login`, `token (JWT)`, `isactive`, más eco de datos.
- `email` único y validado por **regex** (configurable).
- `password` validado por **regex** (configurable).
- `token` persistido junto al usuario.
- Persistencia con **JPA** + **H2 en memoria**.
- Build con **Maven**.
- **Java 17** (Java 8+ requerido).
- **Swagger UI** y **OpenAPI**.
- **Pruebas** unitarias / de capa web.
- **Diagrama** PlantUML (`docs/diagram.puml`).

## Cómo ejecutar
```bash
mvn spring-boot:run
# o
mvn clean package && java -jar target/user-registration-0.0.1-SNAPSHOT.jar
```

Swagger UI: http://localhost:8080/swagger-ui.html

H2 Console (solo para ver datos): http://localhost:8080/h2-console (JDBC: `jdbc:h2:mem:usersdb`, user `sa`, sin password).

## Ejemplo de request
```bash
curl -X POST http://localhost:8080/api/v1/users   -H "Content-Type: application/json"   -d '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Password1",
    "phones": [
      { "number": "1234567", "citycode": "1", "contrycode": "57" }
    ]
  }'
```

> Se acepta la clave `contrycode` (tal como está en el enunciado) gracias a `@JsonAlias`, y se mapea a `countrycode` en la base de datos.

## Ejemplo de respuesta (201)
```json
{
  "id": "3b2e5f8d-3b62-4f9d-b9cf-9c5b1f0d2b1a",
  "created": "2025-08-11T20:20:00Z",
  "modified": "2025-08-11T20:20:00Z",
  "last_login": "2025-08-11T20:20:00Z",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "isactive": true,
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "phones": [{ "number":"1234567","citycode":"1","countrycode":"57"}]
}
```

## Errores (solo JSON)
- Email duplicado:
```json
{"mensaje": "Correo ya registrado"}
```
- Email inválido / password inválida:
```json
{"mensaje": "Formato del correo es inválido"}
```
```json
{"mensaje": "Formato de la clave es inválido"}
```

- Validaciones de Bean Validation (campo requerido, etc.) devuelven el primer mensaje en `mensaje`.

## Configuración
Regex configurables en `application.yml`:
```yaml
app:
  validation:
    emailRegex: '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'
    passwordRegex: '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$'
```

## Notas de seguridad
- **En producción** se debe **hashear la contraseña** (por ejemplo `BCryptPasswordEncoder`).
- El endpoint está **abierto** porque el alcance de la prueba solo exige generar y persistir el token (no proteger endpoints).

## Diagrama
Revisa `docs/diagram.puml` (PlantUML).
