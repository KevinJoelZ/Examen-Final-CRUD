package com.example.exmencrud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exmencrud.databinding.ActivityIngresarProductBinding

class IngresarProduct : AppCompatActivity() {
    private lateinit var binding: ActivityIngresarProductBinding
    private lateinit var productoDatabase: ProductoDatabase
    private var productoActual: Producto? = null
    private var modoEdicion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar base de datos.
        productoDatabase = ProductoDatabase(this)

        // Configurar botones.
        configurarBotones()

        // Verificar si viene en modo edición
        verificarModoEdicion()
    }

    private fun configurarBotones() {
        binding.btnGuardar.setOnClickListener {
            if (modoEdicion) {
                modificarProducto()
            } else {
                guardarProducto()
            }
        }
//MOLO ELIMINAR..
        binding.btnEliminar.setOnClickListener {
            if (modoEdicion && productoActual != null) {
                mostrarDialogoConfirmarEliminacion()
            } else {
                Toast.makeText(this, "No hay producto seleccionado para eliminar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnModificar.setOnClickListener {
            if (modoEdicion && productoActual != null) {
                modificarProducto()
            } else {
                Toast.makeText(this, "No hay producto seleccionado para modificar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnMostrarProductos.setOnClickListener {
            mostrarListaProductos()
        }
    }

    private fun verificarModoEdicion() {
        val productoId = intent.getIntExtra("producto_id", -1)
        if (productoId != -1) {
            modoEdicion = true
            productoActual = productoDatabase.obtenerProductoPorId(productoId)
            if (productoActual != null) {
                cargarDatosProducto(productoActual!!)
                binding.btnGuardar.text = "Actualizar Producto"
            }
        }
    }

    private fun cargarDatosProducto(producto: Producto) {
        binding.nameProduct.setText(producto.name)
        binding.descriptionProduct.setText(producto.description)
        binding.priceProduct.setText(producto.price.toString())
        binding.stockProduct.setText(producto.stock.toString())
        binding.categoryProduct.setText(producto.category)
    }

    private fun guardarProducto() {
        val producto = validarCampos()
        if (producto != null) {
            try {
                val id = productoDatabase.agregarProducto(producto)
                if (id != -1L) {
                    Toast.makeText(this, "Producto guardado exitosamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                    // Regresar a MainActivity con resultado
                    val intent = Intent()
                    intent.putExtra("producto_guardado", true)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar el producto", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun modificarProducto() {
        val producto = validarCampos()
        if (producto != null && productoActual != null) {
            try {
                val productoActualizado = producto.copy(id = productoActual!!.id)
                val filasAfectadas = productoDatabase.actualizarProducto(productoActualizado)
                if (filasAfectadas > 0) {
                    Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
                    // Regresar a MainActivity con resultado
                    val intent = Intent()
                    intent.putExtra("producto_actualizado", true)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarDialogoConfirmarEliminacion() {
        AlertDialog.Builder(this)
            .setTitle("Confirmar Eliminación")
            .setMessage("¿Estás seguro de que quieres eliminar el producto '${productoActual?.name}'?")
            .setPositiveButton("Eliminar") { _, _ ->
                eliminarProducto()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    //Llama a la función eliminarProducto de tu clase ProductoDatabase...
    //Si la eliminación fue exitosa (filas afectadas > 0), muestra un mensaje y cierra la pantalla...
    private fun eliminarProducto() {
        if (productoActual != null) {
            try {
                val filasAfectadas = productoDatabase.eliminarProducto(productoActual!!.id)
                if (filasAfectadas > 0) {
                    Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
                    // Regresar a MainActivity con resultado
                    val intent = Intent()
                    intent.putExtra("producto_eliminado", true)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarListaProductos() {
        val intent = Intent(this, ListaProductosActivity::class.java)
        startActivity(intent)
    }

    private fun validarCampos(): Producto? {
        val nombre = binding.nameProduct.text.toString().trim()
        val descripcion = binding.descriptionProduct.text.toString().trim()
        val precioStr = binding.priceProduct.text.toString().trim()
        val stockStr = binding.stockProduct.text.toString().trim()
        val categoria = binding.categoryProduct.text.toString().trim()

        // Validar campos vacíos
        if (nombre.isEmpty()) {
            binding.nameProduct.error = "El nombre es obligatorio"
            return null
        }

        if (descripcion.isEmpty()) {
            binding.descriptionProduct.error = "La descripción es obligatoria"
            return null
        }

        if (precioStr.isEmpty()) {
            binding.priceProduct.error = "El precio es obligatorio"
            return null
        }

        if (stockStr.isEmpty()) {
            binding.stockProduct.error = "El stock es obligatorio"
            return null
        }

        if (categoria.isEmpty()) {
            binding.categoryProduct.error = "La categoría es obligatoria"
            return null
        }

        // Validar precio
        val precio = precioStr.toDoubleOrNull()
        if (precio == null || precio <= 0) {
            binding.priceProduct.error = "El precio debe ser un número válido mayor a 0"
            return null
        }

        // Validar stock
        val stock = stockStr.toIntOrNull()
        if (stock == null || stock < 0) {
            binding.stockProduct.error = "El stock debe ser un número entero válido mayor o igual a 0"
            return null
        }

        // Si todas las validaciones pasan, crear el producto
        return Producto(
            name = nombre,
            description = descripcion,
            price = precio,
            stock = stock,
            category = categoria
        )
    }

    private fun limpiarCampos() {
        binding.nameProduct.text.clear()
        binding.descriptionProduct.text.clear()
        binding.priceProduct.text.clear()
        binding.stockProduct.text.clear()
        binding.categoryProduct.text.clear()
        binding.nameProduct.requestFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        productoDatabase.close()
    }
}