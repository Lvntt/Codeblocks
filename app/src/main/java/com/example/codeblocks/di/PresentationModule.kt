package com.example.codeblocks.di

import com.example.codeblocks.presentation.CodeEditorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {
    viewModel {
        CodeEditorViewModel()
    }
}