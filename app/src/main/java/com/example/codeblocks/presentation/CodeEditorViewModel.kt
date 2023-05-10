package com.example.codeblocks.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.codeblocks.domain.entity.Block
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class CodeEditorViewModel : ViewModel() {

    private val _programBlocks: MutableState<MutableList<Block>> = mutableStateOf(mutableListOf())
    val programBlocks: State<List<Block>> = _programBlocks

    fun onAddBlockClick(blockToCreate: KClass<out Block>) {
        _programBlocks.value.add(blockToCreate.createInstance())
    }

}