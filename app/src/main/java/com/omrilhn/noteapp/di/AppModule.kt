package com.omrilhn.noteapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class) // We want to create it into different instances
@Module
object AppModule {
}