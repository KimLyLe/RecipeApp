package com.example.recipeapp.model

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type


internal class StepDeserializer : JsonDeserializer<Step> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement,
        type: Type,
        jdc: JsonDeserializationContext
    ): Step {
        // Get the "content" element from the parsed JSON
        val step = je.asJsonObject.get("steps")

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson<Step>(step, Step::class.java!!)

    }
}