package com.example.exmencrud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exmencrud.databinding.ItemProductoBinding

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onEditarClick: (Producto) -> Unit,
    private val onEliminarClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(private val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Producto, onEditarClick: (Producto) -> Unit, onEliminarClick: (Producto) -> Unit) {
            binding.tvNombreProducto.text = "Nombre: ${producto.name}"
            binding.tvDescripcionProducto.text = "Descripción: ${producto.description}"
            binding.tvPrecioProducto.text = "Precio: $${producto.price}"
            binding.tvStockProducto.text = "Stock: ${producto.stock}"
            binding.tvCategoriaProducto.text = "Categoría: ${producto.category}"
            
            binding.btnEditarProducto.setOnClickListener {
                onEditarClick(producto)
            }
            // Este botón aparece en cada producto del RecyclerView.
// Al presionar, se llama al callback de eliminación definido en la actividad.
            binding.btnEliminarProducto.setOnClickListener {
                onEliminarClick(producto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto, onEditarClick, onEliminarClick)
    }

    override fun getItemCount(): Int = productos.size
} 