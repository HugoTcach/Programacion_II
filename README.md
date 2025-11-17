📦 TPI – Sistema de Gestión de Pedidos y Envíos (Java + JDBC)
Tecnicatura Universitaria en Programación – Programación II

Este proyecto implementa un sistema de gestión de Pedidos y Envíos, siguiendo una arquitectura profesional de 4 capas, utilizando Java, JDBC, MySQL y buenas prácticas de Programación Orientada a Objetos.

Incluye:

CRUD completo para Pedidos y Envíos

Eliminación lógica (soft delete)

Búsquedas por ID y por campo único

Transacción completa para crear un pedido con su envío asociado

Manejo de excepciones y rollback

DAO Pattern

Validaciones de negocio en la capa Service

Menú interactivo por consola

🏗️ Arquitectura del Proyecto

El sistema está organizado en cuatro capas completamente separadas, logrando bajo acoplamiento y alta cohesión:

/main       → Capa de Presentación (Menú y entrada de usuario)
 /service   → Capa de Negocio (Reglas, validaciones, transacciones)
   /dao     → Capa de Persistencia (SQL, JDBC, CRUD)
 /entities  → Capa de Modelos (POJOs)

🔹 1. Entities (Modelos)

Contienen las clases:

Pedido

Envio

Ambas poseen:

Encapsulamiento total (atributos privados + getters/setters)

Métodos sobrescritos (toString, equals, hashCode)

Estados representados con enums

🔹 2. DAO Layer

Implementa el DAO Pattern:

GenericDao<T>

PedidoDao, EnvioDao

PedidoDaoImpl, EnvioDaoImpl

Responsabilidades:

Ejecutar SQL (100% con PreparedStatement)

CRUD completo

Queries separadas como constantes

Recuperación de claves autogeneradas (RETURN_GENERATED_KEYS)

Uso consistente de try-with-resources

🔹 3. Service Layer

Orquesta la lógica de negocio.

Interfaces:

GenericService<T>

PedidoService

EnvioService

Implementaciones:

PedidoServiceImpl

EnvioServiceImpl

Responsabilidades:

Validaciones de entrada

Control de unicidad

Regla de negocio: crear pedido completo + envío en una única transacción

Manejo de commit / rollback

Propagación controlada de excepciones

🔹 4. Main (Presentación)

Incluye:

MenuDisplay → Muestra menús y submenús dinámicamente

Main → Controlador del flujo general

PedidoController, EnvioController

Funciones:

Crear, actualizar, listar, buscar, eliminar

Búsquedas por campo único

Opción especial:

Crear Pedido Completo con transacción

🔒 Gestión de Transacciones

El sistema permite crear un pedido y su envío asociado de forma atómica:

conn.setAutoCommit(false)

insertPedido()
insertEnvio()

commit()


Si ocurre un error (por ejemplo, un tracking duplicado debido al constraint UNIQUE), se ejecuta:

rollback()


Y nada se guarda en la base de datos.

Esto fue validado y puede demostrarse en video.

🔍 Validaciones Implementadas
✔️ En capa Service:

Campos obligatorios

Fechas válidas

Costo y total positivos

Unicidad de número de pedido

Unicidad de tracking

Estado válido según enum

✔️ En base de datos:

UNIQUE en número de pedido

UNIQUE en tracking

FOREIGN KEY en envío → pedido

Soft delete con campo activo

📂 Estructura de Carpetas
src/
 ├── main/
 │    ├── java/
 │    │     ├── utn/tfi/programacion2/main/
 │    │     ├── utn/tfi/programacion2/service/
 │    │     ├── utn/tfi/programacion2/dao/
 │    │     ├── utn/tfi/programacion2/entities/
 │    │     └── utn/tfi/programacion2/utils/
 │    └── resources/
 └── test/

🗄️ Base de Datos

Requisitos:

MySQL 8+

JDBC Driver

Tablas:

pedidos

envios

Incluye:

FK (pedido_id)

Unicidad de campos

Soft delete

▶️ Cómo Ejecutar el Proyecto

Importar en NetBeans / IntelliJ / Eclipse

Crear la BD usando el script SQL provisto

Configurar credenciales en ConnectionManager

Ejecutar la clase:

Main.java


Navegar por el menú:

1. Gestionar Pedidos
2. Gestionar Envios

📌 Funcionalidades Principales
✔️ CRUD completo de Pedidos
✔️ CRUD completo de Envíos
✔️ Búsqueda por ID
✔️ Búsqueda por campo único

Buscar pedido por número

Buscar envío por tracking

✔️ Eliminación lógica

Los registros quedan ocultos para “listar” pero existen en BD.

✔️ Crear Pedido Completo (Transacción)

Incluye rollback automático frente a errores.

🎥 Caso de Prueba Destacado: Rollback

Se puede demostrar un rollback generando un tracking duplicado:

Pedido válido

Envío con tracking repetido

Salida esperada:

Transacción revertida por error: Duplicate entry 'DUPLI001' for key 'tracking'
ERROR: Error al crear pedido completo: Duplicate entry 'DUPLI001'

🧪 Requisitos y Dependencias

Java 17+

JDBC driver MySQL

MySQL 8+

Maven (opcional)

👥 Autores

Hugo Tkach

Ezequiel Ventura

Farid …

Ezequiel …

(Agregar todos los integrantes)

📘 Licencia

Este proyecto es de uso académico para la materia Programación II – UTN.
