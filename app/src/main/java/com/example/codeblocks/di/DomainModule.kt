package com.example.codeblocks.di

import com.example.codeblocks.data.repository.ConsoleRepositoryImpl
import com.example.codeblocks.domain.repository.ConsoleRepository
import com.example.codeblocks.domain.usecases.ReadFromConsoleUseCase
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideConsoleRepository(): ConsoleRepository = ConsoleRepositoryImpl()

fun provideWriteToConsoleUseCase(repository: ConsoleRepository): WriteToConsoleUseCase =
    WriteToConsoleUseCase(repository)

fun provideReadFromConsoleUseCase(repository: ConsoleRepository): ReadFromConsoleUseCase =
    ReadFromConsoleUseCase(repository)

fun provideDomainModule(): Module = module {
    single {
        provideConsoleRepository()
    }
    single {
        provideWriteToConsoleUseCase(get())
    }
    single {
        provideReadFromConsoleUseCase(get())
    }
}