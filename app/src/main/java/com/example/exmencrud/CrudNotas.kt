package com.example.exmencrud

class CrudNotas { AppCompatActivity()
    {
        private lateinit var binding: ActivityCrudNotasBinding

        private val data = mutableListOf<Notas>()
        private var notaEdit: Notas? = null
        var id = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            binding = ActivityCrudNotasBinding.inflate(layoutInflater)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            binding.btnGuardar.setOnClickListener {
                guardarNota()
            }
            binding.btnMostrar.setOnClickListener {
                mostrarNotas()
            }
            binding.btnEliminar.setOnClickListener {
                eliminarNota()
            }
            binding.btnSeleccionar.setOnClickListener {
                seleccionarNota()
            }
            binding.btnEditar.setOnClickListener {
                editarNota()
            }
        }//llave de cierre del metodo oncreate

