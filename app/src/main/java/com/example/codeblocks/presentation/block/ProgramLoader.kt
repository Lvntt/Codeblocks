package com.example.codeblocks.presentation.block

import android.content.Context
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.data.SimpleBlockData
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import com.example.codeblocks.presentation.block.parameters.CastExpressionParameters
import com.example.codeblocks.presentation.block.parameters.EmptyParameters
import com.example.codeblocks.presentation.block.parameters.ForLoopBlockParameters
import com.example.codeblocks.presentation.block.parameters.FunctionCallParameters
import com.example.codeblocks.presentation.block.parameters.FunctionDeclarationParameters
import com.example.codeblocks.presentation.block.parameters.FunctionReturnParameters
import com.example.codeblocks.presentation.block.parameters.IfBlockParameters
import com.example.codeblocks.presentation.block.parameters.ListExpressionParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.presentation.block.parameters.ThreeExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.TwoExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Type
import kotlin.reflect.KClass

private val _blockDataTypeAdapter = RuntimeTypeAdapterFactory.of(BlockData::class.java, "type")
    .registerSubtype(
        BlockWithNestingData::class.java,
        BlockWithNestingData::class.qualifiedName
    )
    .registerSubtype(ExpressionBlockData::class.java, ExpressionBlockData::class.qualifiedName)
    .registerSubtype(SimpleBlockData::class.java, SimpleBlockData::class.qualifiedName)

private val _blockParametersTypeAdapter =
    RuntimeTypeAdapterFactory.of(BlockParameters::class.java, "paramType")
        .registerSubtype(
            CastExpressionParameters::class.java,
            CastExpressionParameters::class.qualifiedName
        )
        .registerSubtype(EmptyParameters::class.java, EmptyParameters::class.qualifiedName)
        .registerSubtype(
            ForLoopBlockParameters::class.java,
            ForLoopBlockParameters::class.qualifiedName
        )
        .registerSubtype(
            FunctionCallParameters::class.java,
            FunctionCallParameters::class.qualifiedName
        )
        .registerSubtype(
            FunctionDeclarationParameters::class.java,
            FunctionDeclarationParameters::class.qualifiedName
        )
        .registerSubtype(
            FunctionReturnParameters::class.java,
            FunctionReturnParameters::class.qualifiedName
        )
        .registerSubtype(IfBlockParameters::class.java, IfBlockParameters::class.qualifiedName)
        .registerSubtype(
            ListExpressionParameters::class.java,
            ListExpressionParameters::class.qualifiedName
        )
        .registerSubtype(
            SingleExpressionParameter::class.java,
            SingleExpressionParameter::class.qualifiedName
        )
        .registerSubtype(
            StringExpressionParameter::class.java,
            StringExpressionParameter::class.qualifiedName
        )
        .registerSubtype(
            ThreeExpressionBlockParameters::class.java,
            ThreeExpressionBlockParameters::class.qualifiedName
        )
        .registerSubtype(
            TwoExpressionBlockParameters::class.java,
            TwoExpressionBlockParameters::class.qualifiedName
        )
        .registerSubtype(
            VariableAssignmentBlockParameters::class.java,
            VariableAssignmentBlockParameters::class.qualifiedName
        )
        .registerSubtype(
            VariableDeclarationBlockParameters::class.java,
            VariableDeclarationBlockParameters::class.qualifiedName
        )

object ProgramLoader {
    fun saveProgram(blocksRoot: List<BlockData>, filename: String, context: Context) {
        val gson = GsonBuilder().registerTypeAdapter(KClass::class.java, KClassAdapter).create()

        val file = File(context.filesDir, filename)
        val jsonProgram = gson.toJson(blocksRoot)

        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(jsonProgram.toByteArray())
        fileOutputStream.close()
    }

    fun getSavedPrograms(context: Context): Array<out File>? = context.filesDir.listFiles()

    fun openSavedProgram(savedProgram: File): MutableList<BlockData> {
        val gson = GsonBuilder().registerTypeAdapter(KClass::class.java, KClassAdapter)
            .registerTypeAdapterFactory(_blockDataTypeAdapter)
            .registerTypeAdapterFactory(_blockParametersTypeAdapter).create()

        val importedRootProgramBlocks = savedProgram.readText()
        return gson.fromJson(importedRootProgramBlocks, Array<BlockData>::class.java)
            .toMutableList()
    }
}

private object KClassAdapter : JsonSerializer<KClass<*>>, JsonDeserializer<KClass<*>> {
    override fun serialize(
        src: KClass<*>?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        if (src != null) {
            return JsonPrimitive(src.qualifiedName)
        }
        return JsonNull.INSTANCE
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): KClass<*> {
        if (json != null) {
            return Class.forName(json.asString).kotlin
        }
        return Any::class
    }

}