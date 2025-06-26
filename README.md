# Aplicación de Gestión de Productos CRUD

Esta es una aplicación Android que permite gestionar productos con operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar).

## Características

- **Interfaz moderna**: Diseño Material Design 3 con tarjetas y componentes modernos
- **Operaciones CRUD completas**:
  - **Crear**: Agregar nuevos productos con validación de campos
  - **Leer**: Mostrar lista de productos en un RecyclerView
  - **Actualizar**: Editar productos existentes
  - **Eliminar**: Eliminar productos con confirmación

## Funcionalidades

### Gestión de Productos
- Nombre del producto
- Descripción detallada
- Precio (con formato de moneda)
- Stock disponible
- Categoría

### Validaciones
- Campos obligatorios
- Precio debe ser mayor a 0
- Stock no puede ser negativo
- Validación de tipos de datos

### Interfaz de Usuario
- RecyclerView con diseño de tarjetas
- FloatingActionButton para agregar productos
- Diálogos modales para agregar/editar
- Confirmación para eliminar
- Estado vacío cuando no hay productos
- Datos de ejemplo precargados

## Estructura del Proyecto

```
app/src/main/
├── java/com/example/exmencrud/
│   ├── MainActivity.kt          # Actividad principal con lógica CRUD
│   ├── Producto.kt             # Clase de datos para productos
│   └── ProductoAdapter.kt      # Adaptador para RecyclerView
└── res/layout/
    ├── activity_main.xml       # Layout principal
    ├── item_producto.xml       # Layout para cada producto
    └── dialog_producto.xml     # Layout para diálogo de agregar/editar
```

## Cómo usar

1. **Agregar Producto**: Toca el botón flotante (+) en la esquina inferior derecha
2. **Editar Producto**: Toca el botón "Editar" en cualquier tarjeta de producto
3. **Eliminar Producto**: Toca el botón "Eliminar" en cualquier tarjeta de producto
4. **Ver Productos**: Los productos se muestran automáticamente en la lista

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal
- **AndroidX**: Librerías modernas de Android
- **Material Design 3**: Componentes de UI modernos
- **RecyclerView**: Para mostrar la lista de productos
- **AlertDialog**: Para diálogos de confirmación y formularios

## Requisitos

- Android API 24+ (Android 7.0)
- Android Studio Arctic Fox o superior
- Kotlin 1.8+

## Instalación

1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza las dependencias de Gradle
4. Ejecuta la aplicación en un dispositivo o emulador

## Capturas de Pantalla

La aplicación incluye:
- Lista de productos con diseño de tarjetas
- Formulario de agregar/editar productos
- Confirmación de eliminación
- Estado vacío cuando no hay productos
- Interfaz responsive y moderna 