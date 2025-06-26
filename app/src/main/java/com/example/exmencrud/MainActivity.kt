package com.example.exmencrud

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var tvEmptyState: MaterialTextView
    private lateinit var fabAgregarProducto: FloatingActionButton //declarado

    
    private val productos = mutableListOf<Producto>()
    private var nextId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fabAgregarProducto=findViewById(R.id.btnAgregarProducto)
        fabAgregarProducto.setOnClickListener {
            startActivity(Intent(this,IngresarProduct::class.java))
            finish()
        }
    }
    /*
    private fun inicializarVistas() {
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        tvEmptyState = findViewById(R.id.tvEmptyState)
        fabAgregarProducto = findViewById(R.id.fabAgregarProducto)
    }
    
    private fun configurarRecyclerView() {
        productoAdapter = ProductoAdapter(
            productos = productos,
            onEditarClick = { producto -> mostrarDialogoEditarProducto(producto) },
            onEliminarClick = { producto -> mostrarDialogoConfirmarEliminacion(producto) }
        )
        
        recyclerViewProductos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productoAdapter
        }
    }
    
    private fun configurarEventos() {
        fabAgregarProducto.setOnClickListener {
            mostrarDialogoAgregarProducto()
        }
    }
    
    private fun cargarDatosEjemplo() {
        val productosEjemplo = listOf(
            Producto(nextId++, "Laptop HP Pavilion", "Laptop de alta gama con procesador Intel i7", 1299.99, 15, "Electrónicos"),
            Producto(nextId++, "Mouse Inalámbrico", "Mouse ergonómico con conexión Bluetooth", 29.99, 50, "Accesorios"),
            Producto(nextId++, "Teclado Mecánico", "Teclado gaming con switches Cherry MX", 89.99, 25, "Accesorios"),
            Producto(nextId++, "Monitor 4K", "Monitor de 27 pulgadas con resolución 4K", 399.99, 10, "Electrónicos")
        )
        
        productos.addAll(productosEjemplo)
        productoAdapter.notifyDataSetChanged()
        actualizarEstadoVacio()
    }
    
    private fun mostrarDialogoAgregarProducto() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_producto, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        
        val etNombre = dialogView.findViewById<TextInputEditText>(R.id.etNombreProducto)
        val etDescripcion = dialogView.findViewById<TextInputEditText>(R.id.etDescripcionProducto)
        val etPrecio = dialogView.findViewById<TextInputEditText>(R.id.etPrecioProducto)
        val etStock = dialogView.findViewById<TextInputEditText>(R.id.etStockProducto)
        val etCategoria = dialogView.findViewById<TextInputEditText>(R.id.etCategoriaProducto)
        val btnGuardar = dialogView.findViewById<View>(R.id.btnGuardarProducto)
        val btnCancelar = dialogView.findViewById<View>(R.id.btnCancelarProducto)
        
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()
            val precioStr = etPrecio.text.toString().trim()
            val stockStr = etStock.text.toString().trim()
            val categoria = etCategoria.text.toString().trim()
            
            if (validarCampos(nombre, descripcion, precioStr, stockStr, categoria)) {
                val precio = precioStr.toDouble()
                val stock = stockStr.toInt()
                
                val nuevoProducto = Producto(
                    id = nextId++,
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio,
                    stock = stock,
                    categoria = categoria
                )
                
                productoAdapter.agregarProducto(nuevoProducto)
                actualizarEstadoVacio()
                dialog.dismiss()
                Toast.makeText(this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show()
            }
        }
        
        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun mostrarDialogoEditarProducto(producto: Producto) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_producto, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        
        val tvTitulo = dialogView.findViewById<MaterialTextView>(R.id.tvDialogTitle)
        val etNombre = dialogView.findViewById<TextInputEditText>(R.id.etNombreProducto)
        val etDescripcion = dialogView.findViewById<TextInputEditText>(R.id.etDescripcionProducto)
        val etPrecio = dialogView.findViewById<TextInputEditText>(R.id.etPrecioProducto)
        val etStock = dialogView.findViewById<TextInputEditText>(R.id.etStockProducto)
        val etCategoria = dialogView.findViewById<TextInputEditText>(R.id.etCategoriaProducto)
        val btnGuardar = dialogView.findViewById<View>(R.id.btnGuardarProducto)
        val btnCancelar = dialogView.findViewById<View>(R.id.btnCancelarProducto)
        
        // Llenar campos con datos existentes
        tvTitulo.text = "Editar Producto"
        etNombre.setText(producto.nombre)
        etDescripcion.setText(producto.descripcion)
        etPrecio.setText(producto.precio.toString())
        etStock.setText(producto.stock.toString())
        etCategoria.setText(producto.categoria)
        
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()
            val precioStr = etPrecio.text.toString().trim()
            val stockStr = etStock.text.toString().trim()
            val categoria = etCategoria.text.toString().trim()
            
            if (validarCampos(nombre, descripcion, precioStr, stockStr, categoria)) {
                val precio = precioStr.toDouble()
                val stock = stockStr.toInt()
                
                val productoActualizado = producto.copy(
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio,
                    stock = stock,
                    categoria = categoria
                )
                
                productoAdapter.actualizarProducto(productoActualizado)
                dialog.dismiss()
                Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
        }
        
        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun mostrarDialogoConfirmarEliminacion(producto: Producto) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar Eliminación")
            .setMessage("¿Estás seguro de que quieres eliminar el producto '${producto.nombre}'?")
            .setPositiveButton("Eliminar") { _, _ ->
                productoAdapter.eliminarProducto(producto)
                actualizarEstadoVacio()
                Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    private fun validarCampos(nombre: String, descripcion: String, precio: String, stock: String, categoria: String): Boolean {
        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (descripcion.isEmpty()) {
            Toast.makeText(this, "La descripción es obligatoria", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (precio.isEmpty()) {
            Toast.makeText(this, "El precio es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        
        try {
            val precioValor = precio.toDouble()
            if (precioValor <= 0) {
                Toast.makeText(this, "El precio debe ser mayor a 0", Toast.LENGTH_SHORT).show()
                return false
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "El precio debe ser un número válido", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (stock.isEmpty()) {
            Toast.makeText(this, "El stock es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        
        try {
            val stockValor = stock.toInt()
            if (stockValor < 0) {
                Toast.makeText(this, "El stock no puede ser negativo", Toast.LENGTH_SHORT).show()
                return false
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "El stock debe ser un número entero válido", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (categoria.isEmpty()) {
            Toast.makeText(this, "La categoría es obligatoria", Toast.LENGTH_SHORT).show()
            return false
        }
        
        return true
    }
    
    private fun actualizarEstadoVacio() {
        if (productos.isEmpty()) {
            recyclerViewProductos.visibility = View.GONE
            tvEmptyState.visibility = View.VISIBLE
        } else {
            recyclerViewProductos.visibility = View.VISIBLE
            tvEmptyState.visibility = View.GONE
        }
    }

     */
}