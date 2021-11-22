package com.example.todolistbyjetpackcompose.di

import android.content.Context
import com.example.todolistbyjetpackcompose.repository.ListRepository
import com.example.todolistbyjetpackcompose.repository.ListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideListRepository(
        @ApplicationContext context: Context,
    ) : ListRepository {
        return ListRepositoryImpl(context)
    }
}