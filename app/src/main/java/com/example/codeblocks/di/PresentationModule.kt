package com.example.codeblocks.di

import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.presentation.viewmodel.ConsoleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {
    viewModel {
        CodeEditorViewModel(get(), get())
    }
    viewModel {
        ConsoleViewModel(get(), get(), get())
    }
}