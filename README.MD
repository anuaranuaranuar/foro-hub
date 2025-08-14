# Foro Hub API

API REST construida con **Spring Boot** para gestionar usuarios, autenticación y tópicos de un foro.  
La especificación está documentada en OpenAPI 3.1.0 y expuesta vía **Swagger UI**.

## 📌 Requisitos previos

- **Java 17** o superior
- **Maven** 3.8+
- **Spring Boot** 3.x
- Base de datos compatible con JPA/Hibernate (por ejemplo, MySQL o PostgreSQL)
- **Swagger/OpenAPI** habilitado (`springdoc-openapi`)

---

## 🚀 Endpoints principales

### 🔑 Autenticación
| Método | URL     | Descripción |
|--------|---------|-------------|
| `POST` | `/login` | Autenticar usuario y devolver un token JWT |

**Request Body**:
```json
{
  "nombre": "string",
  "correoElectronico": "string",
  "contrasena": "string"
}
```

**Response**:
```json
{
  "DatosToken": "jwt_token_aqui"
}
```

---

### 👤 Usuarios
| Método | URL        | Descripción |
|--------|------------|-------------|
| `POST` | `/usuarios` | Registrar nuevo usuario |

**Request Body**:
```json
{
  "nombre": "string",
  "correoElectronico": "string",
  "contrasena": "string"
}
```

---

### 💬 Tópicos
#### Listar
| Método | URL        | Descripción |
|--------|------------|-------------|
| `GET`  | `/topicos` | Listar tópicos con paginación y filtros |

**Parámetros opcionales**:
- `curso` (string)
- `year` (int)
- `pageable` (objeto con `page`, `size`, `sort`)

#### Crear
| Método | URL        | Descripción |
|--------|------------|-------------|
| `POST` | `/topicos` | Crear un nuevo tópico |

**Request Body**:
```json
{
  "titulo": "string",
  "mensaje": "string",
  "autor": 1,
  "curso": 1
}
```

#### Obtener por ID
| Método | URL             | Descripción |
|--------|-----------------|-------------|
| `GET`  | `/topicos/{id}` | Obtener un tópico por su ID |

#### Actualizar
| Método | URL             | Descripción |
|--------|-----------------|-------------|
| `PUT`  | `/topicos/{id}` | Actualizar título y mensaje |

#### Deshabilitar
| Método | URL              | Descripción |
|--------|------------------|-------------|
| `PATCH`| `/topicos/{id}`  | Deshabilitar un tópico |

---

## 🔐 Autenticación JWT
La mayoría de los endpoints requieren un **token JWT** en el header:

```
Authorization: Bearer <token>
```

El token se obtiene al hacer `POST /login`.

---

## 📄 Documentación Swagger
Una vez levantada la aplicación, la documentación interactiva está disponible en:

- **Swagger UI**: `http://localhost:8081/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:8081/v3/api-docs`

---

## 🛠 Ejecución local
```bash
# Compilar y empaquetar
mvn clean install

# Ejecutar
mvn spring-boot:run
```

---

## 📂 Estructura básica del proyecto
```
src/main/java/com/tuempresa/foro
    ├── config/        # Configuración de seguridad y beans
    ├── controllers/   # Controladores REST
    ├── dto/           # Objetos de transferencia de datos
    ├── entities/      # Entidades JPA
    ├── repository/    # Repositorios Spring Data JPA
    ├── security/      # Filtros y utilidades JWT
    └── services/      # Lógica de negocio
```

---

## ✨ Notas
- Endpoints públicos: `/usuarios`, `/login`, `/swagger-ui/**`, `/v3/api-docs/**`
- Endpoints protegidos requieren JWT válido
- Contraseñas almacenadas con **BCrypt**