# 📱 Sistema CRUD de Productos - Android

Una aplicación Android completa para la gestión de productos con operaciones CRUD (Crear, Leer, Actualizar, Eliminar) implementadas con SQLite, ViewBinding y Material Design.

## 🎯 Características Principales

### ✅ **Operaciones CRUD Completas**
- **CREATE**: Agregar nuevos productos con validaciones robustas
- **READ**: Mostrar lista de productos con RecyclerView y Material Cards
- **UPDATE**: Editar productos existentes con modo edición automático
- **DELETE**: Eliminar productos con confirmación de seguridad

### 🎨 **Interfaz Moderna**
- **Material Design 3**: Componentes modernos y atractivos
- **ViewBinding**: Acceso seguro a vistas sin findViewById
- **Responsive Design**: Adaptable a diferentes tamaños de pantalla
- **Navegación Fluida**: Entre actividades con transiciones suaves

### 🛡️ **Validaciones Robustas**
- Campos obligatorios con mensajes de error específicos
- Precio debe ser número válido mayor a 0
- Stock debe ser número entero mayor o igual a 0
- Validación de tipos de datos en tiempo real

## 🏗️ Arquitectura del Proyecto

### **Estructura de Archivos**
```
app/src/main/java/com/example/exmencrud/
├── MainActivity.kt                    # Pantalla principal con navegación
├── IngresarProduct.kt                 # Formulario CRUD completo
├── ListaProductosActivity.kt          # Lista de productos con RecyclerView
├── ProductoAdapter.kt                 # Adaptador para RecyclerView
├── ProductoDatabase.kt                # Base de datos SQLite
└── Producto.kt                        # Modelo de datos

app/src/main/res/layout/
├── activity_main.xml                  # Pantalla de inicio
├── activity_ingresar_product.xml      # Formulario de productos
├── activity_lista_productos.xml       # Lista de productos
└── item_producto.xml                  # Item individual de producto
```

### **Base de Datos SQLite**
- **Tabla**: `productos`
- **Campos**: id, name, description, price, stock, category
- **Operaciones**: INSERT, SELECT, UPDATE, DELETE
- **Búsqueda**: Por nombre con LIKE

## 🚀 Funcionalidades Implementadas

### **1. Pantalla Principal (MainActivity)**
- ✅ Navegación a "Agregar Producto"
- ✅ Navegación a "Ver Lista de Productos"
- ✅ Diseño limpio y moderno

### **2. Gestión de Productos (IngresarProduct)**
- ✅ **Guardar Producto**: Validación completa y persistencia
- ✅ **Modificar Producto**: Edición de productos existentes
- ✅ **Eliminar Producto**: Eliminación con confirmación
- ✅ **Validar Campos**: Validaciones en tiempo real
- ✅ **Modo Edición**: Carga automática de datos

### **3. Lista de Productos (ListaProductosActivity)**
- ✅ **RecyclerView**: Lista eficiente con Material Cards
- ✅ **FloatingActionButton**: Agregar nuevos productos
- ✅ **Botones de Acción**: Editar y eliminar por producto
- ✅ **Estado Vacío**: Mensaje cuando no hay productos
- ✅ **Actualización Automática**: Refresca lista después de cambios

### **4. Base de Datos (ProductoDatabase)**
- ✅ **SQLite**: Persistencia local de datos
- ✅ **CRUD Operations**: Todas las operaciones implementadas
- ✅ **Búsqueda**: Por nombre de producto
- ✅ **Manejo de Errores**: Try-catch en todas las operaciones

## 📱 Cómo Usar la Aplicación

### **Flujo de Navegación**
```
MainActivity (Inicio)
    ↓
├── "Agregar Producto" → IngresarProduct (CREATE)
    ↓
└── "Ver Lista de Productos" → ListaProductosActivity (READ)
    ↓
    └── Botón "Editar" → IngresarProduct (UPDATE)
    └── Botón "Eliminar" → Confirmación → DELETE
```

### **Pasos de Uso**

1. **Agregar Producto**:
   - Toca "Agregar Producto" en la pantalla principal
   - Completa todos los campos (nombre, descripción, precio, stock, categoría)
   - Toca "Guardar Producto"
   - El producto se guarda en la base de datos

2. **Ver Productos**:
   - Toca "Ver Lista de Productos" en la pantalla principal
   - Se muestra la lista de todos los productos guardados
   - Cada producto se muestra en una tarjeta Material Design

3. **Editar Producto**:
   - En la lista de productos, toca "Editar" en cualquier producto
   - Se abre el formulario con los datos precargados
   - Modifica los campos necesarios
   - Toca "Actualizar Producto"

4. **Eliminar Producto**:
   - En la lista de productos, toca "Eliminar" en cualquier producto
   - Confirma la eliminación en el diálogo
   - El producto se elimina de la base de datos

## 🛠️ Tecnologías y Librerías

### **Lenguajes y Frameworks**
- **Kotlin**: Lenguaje de programación principal
- **Android SDK**: Framework de desarrollo Android
- **SQLite**: Base de datos local

### **Librerías de Android**
- **AndroidX**: Librerías modernas de Android
- **Material Design**: Componentes de UI modernos
- **ViewBinding**: Acceso seguro a vistas
- **RecyclerView**: Lista eficiente de elementos
- **ConstraintLayout**: Layouts flexibles y responsivos

### **Dependencias Principales**
```kotlin
implementation("androidx.core:core-ktx")
implementation("androidx.appcompat:appcompat")
implementation("com.google.android.material:material")
implementation("androidx.activity:activity")
implementation("androidx.constraintlayout:constraintlayout")
implementation("androidx.recyclerview:recyclerview:1.3.2")
implementation("androidx.cardview:cardview:1.0.0")
```

## 📋 Requisitos del Sistema

### **Desarrollo**
- **Android Studio**: Arctic Fox o superior
- **Kotlin**: 1.8+
- **JDK**: 17 o superior
- **Gradle**: 8.11.1

### **Ejecución**
- **Android API**: 24+ (Android 7.0 Nougat)
- **RAM**: Mínimo 2GB
- **Almacenamiento**: 50MB libres

## 🔧 Instalación y Configuración

### **1. Clonar el Repositorio**
```bash
git clone https://github.com/tu-usuario/ExmenCRUD.git
cd ExmenCRUD
```

### **2. Abrir en Android Studio**
- Abre Android Studio
- Selecciona "Open an existing project"
- Navega a la carpeta del proyecto y selecciónala

### **3. Configurar el Entorno**
- Asegúrate de tener instalado el **JDK 17 o superior**
- Sincroniza las dependencias de Gradle
- Verifica que ViewBinding esté habilitado

### **4. Ejecutar la Aplicación**
- Conecta un dispositivo Android o inicia un emulador
- Presiona el botón "Run" (▶️) en Android Studio
- La aplicación se instalará y ejecutará automáticamente

## 🐛 Solución de Problemas

### **Error de Compilación Java**
Si encuentras el error:
```
Toolchain installation does not provide the required capabilities: [JAVA_COMPILER]
```

**Solución**:
1. Instala el **JDK** (no solo el JRE) desde [Adoptium](https://adoptium.net/)
2. Configura la variable de entorno `JAVA_HOME`
3. Reinicia Android Studio

### **Error de ViewBinding**
Si ViewBinding no funciona:
1. Verifica que esté habilitado en `build.gradle.kts`
2. Ejecuta `./gradlew clean build`
3. Sincroniza el proyecto

## 📊 Características Técnicas

### **Patrones de Diseño**
- **MVVM**: Separación de lógica de negocio y UI
- **Repository Pattern**: Acceso a datos centralizado
- **Adapter Pattern**: Para RecyclerView

### **Manejo de Datos**
- **SQLite**: Base de datos local persistente
- **ContentValues**: Para operaciones de base de datos
- **Cursor**: Para consultas de datos

### **UI/UX**
- **Material Design 3**: Componentes modernos
- **Responsive Design**: Adaptable a diferentes pantallas
- **Error Handling**: Mensajes de error claros
- **Loading States**: Indicadores de carga

## 🎨 Capturas de Pantalla

La aplicación incluye:
- **Pantalla Principal**: Navegación limpia y moderna
- **Formulario de Productos**: Campos validados y responsive
- **Lista de Productos**: Tarjetas Material Design con acciones
- **Confirmaciones**: Diálogos de seguridad para eliminación
- **Estados Vacíos**: Mensajes informativos cuando no hay datos

## 📝 Notas de Desarrollo

### **Mejoras Implementadas**
- ✅ Eliminación de código redundante
- ✅ Uso consistente de ViewBinding
- ✅ Validaciones robustas
- ✅ Manejo de errores completo
- ✅ Navegación fluida entre actividades
- ✅ Persistencia de datos con SQLite

### **Buenas Prácticas**
- Código limpio y bien documentado
- Separación de responsabilidades
- Manejo de excepciones
- Validaciones de entrada
- Interfaz de usuario intuitiva

## 🤝 Contribuciones

Para contribuir al proyecto:
1. Fork el repositorio
2. Crea una rama para tu feature
3. Realiza tus cambios
4. Envía un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

**Desarrollado con ❤️ usando Kotlin y Android** 