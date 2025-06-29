package com.example.exmencrud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exmencrud.databinding.ActivityListaProductosBinding

class ListaProductosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaProductosBinding
    private lateinit var productoDatabase: ProductoDatabase
    private lateinit var adapter: ProductoAdapter
    private val productos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productoDatabase = ProductoDatabase(this)
        
        configurarRecyclerView()
        cargarProductos()
        
        binding.fabAgregarProducto.setOnClickListener {
            val intent = Intent(this, IngresarProduct::class.java)
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_PRODUCTO)
        }
    }

    private fun configurarRecyclerView() {
        adapter = ProductoAdapter(
            productos = productos,
            onEditarClick = { producto -> editarProducto(producto) },
            onEliminarClick = { producto -> confirmarEliminacion(producto) }
        )
        
        binding.recyclerViewProductos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProductos.adapter = adapter
    }

    private fun cargarProductos() {
        productos.clear()
        productos.addAll(productoDatabase.obtenerTodosLosProductos())
        adapter.notifyDataSetChanged()
        
        if (productos.isEmpty()) {
            binding.tvEmptyState.visibility = android.view.View.VISIBLE
            binding.recyclerViewProductos.visibility = android.view.View.GONE
            Toast.makeText(this, "No hay productos guardados", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvEmptyState.visibility = android.view.View.GONE
            binding.recyclerViewProductos.visibility = android.view.View.VISIBLE
        }
    }

    private fun editarProducto(producto: Producto) {
        val intent = Intent(this, IngresarProduct::class.java)
        intent.putExtra("producto_id", producto.id)
        startActivityForResult(intent, REQUEST_CODE_EDITAR_PRODUCTO)
    }

    private fun confirmarEliminacion(producto: Producto) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar Eliminación")
            .setMessage("¿Estás seguro de que quieres eliminar el producto '${producto.name}'?")
            .setPositiveButton("Eliminar") { _, _ ->
                eliminarProducto(producto)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun eliminarProducto(producto: Producto) {
        val filasAfectadas = productoDatabase.eliminarProducto(producto.id)
        if (filasAfectadas > 0) {
            productos.remove(producto)
            adapter.notifyDataSetChanged()
            cargarProductos() // Actualizar estado vacío
            Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_AGREGAR_PRODUCTO -> {
                    Toast.makeText(this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show()
                    cargarProductos()
                }
                REQUEST_CODE_EDITAR_PRODUCTO -> {
                    Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
                    cargarProductos()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        productoDatabase.close()
    }

    companion object {
        private const val REQUEST_CODE_AGREGAR_PRODUCTO = 1001
        private const val REQUEST_CODE_EDITAR_PRODUCTO = 1002
    }
} 