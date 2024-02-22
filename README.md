# Prueba Técnica - Backend

## Descripción
Esta es una prueba técnica de backend que incluye endpoints para la gestión de productos en una aplicación CRUD.

## Endpoints

### Obtener todos los productos
- **Método:** GET
- **Ruta:** `/api/productos`
- **Descripción:** Obtiene todos los productos almacenados en la base de datos.

### Guardar un nuevo producto
- **Método:** POST
- **Ruta:** `/api/productos`
- **Descripción:** Guarda un nuevo producto en la base de datos.
- **Cuerpo de la solicitud:** Objeto Producto en formato JSON.

### Actualizar un producto existente
- **Método:** PUT
- **Ruta:** `/api/productos/{id}`
- **Descripción:** Actualiza un producto existente en la base de datos.
- **Parámetros de la URL:** `id` (identificador del producto a actualizar)
- **Cuerpo de la solicitud:** Objeto Producto actualizado en formato JSON.

### Eliminar un producto
- **Método:** DELETE
- **Ruta:** `/api/productos/{id}`
- **Descripción:** Elimina un producto de la base de datos.
- **Parámetros de la URL:** `id` (identificador del producto a eliminar)

### Calcular valor total del inventario
- **Método:** GET
- **Ruta:** `/api/productos/calcular-valor-total`
- **Descripción:** Calcula el valor total del inventario sumando el precio de todos los productos multiplicado por su cantidad en stock.

### Generar combinaciones de productos
- **Método:** GET
- **Ruta:** `/api/productos/generar-combinaciones`
- **Descripción:** Genera combinaciones de productos cuya suma de precios sea menor o igual al valor objetivo especificado.
- **Parámetros de la URL:** `valorObjetivo` (valor numérico objetivo para la suma de precios)

## Configuración de la base de datos
- **Driver de la base de datos:** `com.mysql.cj.jdbc.Driver`
- **URL de la base de datos:** `jdbc:mysql://localhost:3306/crud_app`
- **Nombre de usuario:** `root`
- **Contraseña:** `root`
- **Estrategia de generación de esquema de la base de datos:** `update`
- **Dialecto de Hibernate:** `org.hibernate.dialect.MySQLDialect`

## Autor
Daniel Piñeros
