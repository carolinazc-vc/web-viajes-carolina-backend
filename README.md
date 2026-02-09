# viajes-carolina-web-backend

Backend de la aplicación de Viajes Carolina construido con Quarkus.

## 🚀 Características Principales

- REST API con Quarkus
- Base de datos PostgreSQL con Hibernate ORM
- Integración con Facebook Graph API para promociones
- Arquitectura limpia (Domain-Driven Design)
- Configuración por perfiles (dev/prod)

## 🔧 Requisitos

- Java 21+
- PostgreSQL 12+
- Gradle 9.1+

## ⚙️ Configuración

### Variables de Entorno

```bash
# Facebook API (requerido para integración)
export FACEBOOK_PAGE_TOKEN="tu_token_aqui"

# Base de datos (opcional, tiene valores por defecto)
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=web-viajes-carolina
export DB_USER=postgres
export DB_PASSWORD=1jerete1
```

### Archivo .env (Desarrollo Local)

Copiar `.env.example` a `.env` y completar valores:

```bash
cp .env.example .env
# Editar .env con tus valores
```

## 🏃 Ejecutar la Aplicación

### Modo Desarrollo

```bash
./gradlew quarkusDev
```

Acceder a:
- Aplicación: http://localhost:8080
- Dev UI: http://localhost:8080/q/dev/
- OpenAPI: http://localhost:8080/q/swagger-ui/

### Modo Producción

```bash
# Build
./gradlew build

# Run
java -jar build/quarkus-app/quarkus-run.jar
```

## 📦 Estructura del Proyecto

```
src/main/
├── java/com/viajescarolina/
│   ├── agency/              # Módulo de agencias
│   ├── content/             # Módulo de contenido
│   │   ├── app/             # Servicios de aplicación
│   │   ├── domain/          # Modelos de dominio
│   │   └── infra/           # Infraestructura (BD, HTTP)
│   ├── destinations/        # Módulo de destinos
│   ├── promotions/          # Módulo de promociones
│   │   ├── app/             # Servicios
│   │   ├── domain/          # FacebookPost, ports
│   │   └── infra/           # FacebookHttpClient
│   └── shared/              # Código compartido
└── resources/
    ├── application.yaml     # Config principal
    └── db/                  # Scripts de BD
```

## 🔗 Integración Facebook

### Características

- ✅ Cliente HTTP para Graph API v24.0
- ✅ Consumo automático de publicaciones
- ✅ Complementa promociones de BD
- ✅ Fallback graceful (sin Facebook = solo BD)
- ✅ Token externalizador en variables de entorno

### Obtener Token de Facebook

Ver: [FACEBOOK_CREDENTIALS_SETUP.md](FACEBOOK_CREDENTIALS_SETUP.md)

### Activar/Desactivar

En `application.yaml`:

```yaml
promotions:
  include-facebook: true   # activar
```

### Documentación Completa

- [FACEBOOK_CLIENT_GUIDE.md](FACEBOOK_CLIENT_GUIDE.md) - Guía técnica
- [FACEBOOK_INTEGRATION_SUMMARY.md](FACEBOOK_INTEGRATION_SUMMARY.md) - Resumen de implementación
- [FACEBOOK_CREDENTIALS_SETUP.md](FACEBOOK_CREDENTIALS_SETUP.md) - Configuración de credenciales

## 📊 API Endpoints

### Promotions

```
GET /promotions           - Listar todas
GET /promotions/{id}      - Obtener una
POST /promotions          - Crear (admin)
PUT /promotions/{id}      - Actualizar (admin)
DELETE /promotions/{id}   - Eliminar (admin)
```

### Home

```
GET /home                 - Obtener home page completo
```

## 🗄️ Base de Datos

### Migraciones

Las migraciones se ejecutan automáticamente al iniciar.

### Schema

Ver scripts en: `src/main/resources/db/`

## 🧪 Testing

```bash
# Run tests
./gradlew test

# Run tests con cobertura
./gradlew test --info
```

## 📝 Logging

Configurado en `application.yaml`:

```yaml
quarkus:
  log:
    level: INFO
```

## 🔒 Seguridad

- ✅ Tokens en variables de entorno (no en código)
- ✅ Validación de entrada
- ✅ CORS configurado
- ✅ HTTPS recomendado en producción

## 📚 Documentación Adicional

- [FACEBOOK_CLIENT_GUIDE.md](FACEBOOK_CLIENT_GUIDE.md) - Guía del cliente Facebook
- [FACEBOOK_INTEGRATION_SUMMARY.md](FACEBOOK_INTEGRATION_SUMMARY.md) - Resumen técnico
- [FACEBOOK_CREDENTIALS_SETUP.md](FACEBOOK_CREDENTIALS_SETUP.md) - Setup de credenciales

## 🤝 Contribuir

1. Fork el proyecto
2. Create una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Open un Pull Request

## 📄 Licencia

MIT License

## 👨‍💼 Soporte

Para soporte, enviar email a: support@viajescarolina.com

---

**Construido con ❤️ usando Quarkus**


## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/viajes-carolina-web-backend-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
