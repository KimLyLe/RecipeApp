package com.example.recipeapp.model

import com.google.gson.*
import java.lang.reflect.Type

internal class EquipmentDeserializer : JsonDeserializer<Equipment> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement,
        type: Type,
        jdc: JsonDeserializationContext
    ): Equipment {
        // Get the "content" element from the parsed JSON
        val equipment = je.asJsonObject.get("ingredients")

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson<Equipment>(equipment, Equipment::class.java!!)

    }
}