package com.example.exmencrud

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductoDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ProductosDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PRODUCTOS = "productos"
        
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_PRICE = "price"
        private const val KEY_STOCK = "stock"
        private const val KEY_CATEGORY = "category"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_PRODUCTOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_PRICE + " REAL,"
                + KEY_STOCK + " INTEGER,"
                + KEY_CATEGORY + " TEXT"
                + ")")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
        onCreate(db)
    }

    fun agregarProducto(producto: Producto): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        
        values.put(KEY_NAME, producto.name)
        values.put(KEY_DESCRIPTION, producto.description)
        values.put(KEY_PRICE, producto.price)
        values.put(KEY_STOCK, producto.stock)
        values.put(KEY_CATEGORY, producto.category)
        
        val id = db.insert(TABLE_PRODUCTOS, null, values)
        db.close()
        return id
    }

    fun obtenerTodosLosProductos(): List<Producto> {
        val productos = mutableListOf<Producto>()
        val selectQuery = "SELECT * FROM $TABLE_PRODUCTOS"
        
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        
        if (cursor.moveToFirst()) {
            do {
                val producto = Producto(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_PRICE)),
                    stock = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_STOCK)),
                    category = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CATEGORY))
                )
                productos.add(producto)
            } while (cursor.moveToNext())
        }
        
        cursor.close()
        db.close()
        return productos
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_PRODUCTOS,
            arrayOf(KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_STOCK, KEY_CATEGORY),
            "$KEY_ID = ?",
            arrayOf(id.toString()),
            null, null, null, null
        )
        
        var producto: Producto? = null
        if (cursor.moveToFirst()) {
            producto = Producto(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                price = cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_PRICE)),
                stock = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_STOCK)),
                category = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CATEGORY))
            )
        }
        
        cursor.close()
        db.close()
        return producto
    }

    fun actualizarProducto(producto: Producto): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        
        values.put(KEY_NAME, producto.name)
        values.put(KEY_DESCRIPTION, producto.description)
        values.put(KEY_PRICE, producto.price)
        values.put(KEY_STOCK, producto.stock)
        values.put(KEY_CATEGORY, producto.category)
        
        val result = db.update(TABLE_PRODUCTOS, values, "$KEY_ID = ?", arrayOf(producto.id.toString()))
        db.close()
        return result
    }
// Función que elimina un producto según su ID desde la base de datos SQLite
    fun eliminarProducto(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_PRODUCTOS, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
        return result
    }

    fun buscarProductosPorNombre(nombre: String): List<Producto> {
        val productos = mutableListOf<Producto>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_PRODUCTOS,
            arrayOf(KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_STOCK, KEY_CATEGORY),
            "$KEY_NAME LIKE ?",
            arrayOf("%$nombre%"),
            null, null, null, null
        )
        
        if (cursor.moveToFirst()) {
            do {
                val producto = Producto(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_PRICE)),
                    stock = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_STOCK)),
                    category = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CATEGORY))
                )
                productos.add(producto)
            } while (cursor.moveToNext())
        }
        
        cursor.close()
        db.close()
        return productos
    }
} 