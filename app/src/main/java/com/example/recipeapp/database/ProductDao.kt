package com.example.recipeapp.database

import androidx.room.*
import com.example.recipeapp.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<Product>

    @Insert
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

    @Update
    suspend fun updateProducts(product: Product)

}