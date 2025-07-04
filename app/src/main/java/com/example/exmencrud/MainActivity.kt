package com.example.exmencrud

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exmencrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        configurarEventos()
    }
    
    private fun configurarEventos() {
        binding.btnAgregarProducto.setOnClickListener {
            val intent = Intent(this, IngresarProduct::class.java)
            startActivity(intent)
        }
        
        binding.btnVerProductos.setOnClickListener {
            val intent = Intent(this, ListaProductosActivity::class.java)
            startActivity(intent)
        }
    }
}