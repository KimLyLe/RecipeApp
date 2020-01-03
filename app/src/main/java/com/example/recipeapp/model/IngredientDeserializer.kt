package com.example.recipeapp.model

import com.google.gson.*
import java.lang.reflect.Type

internal class IngredientDeserializer : JsonDeserializer<Ingredient> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement,
        type: Type,
        jdc: JsonDeserializationContext
    ): Ingredient {
        // Get the "content" element from the parsed JSON
        val ingredient = je.asJsonObject.get("ingredients")

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson<Ingredient>(ingredient, Ingredient::class.java!!)

    }
}