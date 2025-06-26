package com.example.exmencrud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exmencrud.databinding.ActivityIngresarProductBinding


class IngresarProduct : AppCompatActivity() {
    private lateinit var binding: ActivityIngresarProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            guardarProducto()
        }
    }
    private fun guardarProducto(){



        val price = binding.priceProduct.text.toString().toDoubleOrNull()
        val stock = binding.stockProduct.text.toString().toIntOrNull()

        if (price == null || stock == null) {
            Toast.makeText(this, "Precio o stock inválido", Toast.LENGTH_SHORT).show()

        }

        val producto = price?.let {
            stock?.let { it1 ->
                Producto(
                    name = binding.nameProduct.text.toString().trim(),
                    description = binding.descriptionProduct.text.toString().trim(),
                    price = it,
                    stock = it1,
                    category = binding.categoryProduct.text.toString().trim()
                )
            }
        }

        Toast.makeText(this, "Producto ingresado:\n$producto", Toast.LENGTH_LONG).show()
    }
    private fun validarCampos():Producto?{
        if (binding.nameProduct.text.isNullOrBlank() ||
            binding.descriptionProduct.text.isNullOrBlank() ||
            binding.priceProduct.text.isNullOrBlank() ||
            binding.stockProduct.text.isNullOrBlank() ||
            binding.categoryProduct.text.isNullOrBlank()
        ) {}
            //Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()

        return null
    }

}