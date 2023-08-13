package com.omrilhn.noteapp.di

import android.content.Context
import androidx.room.Room
import com.omrilhn.noteapp.data.NoteDatabase
import com.omrilhn.noteapp.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class) // We want to create it into different instances
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase):NoteDatabaseDao
    = noteDatabase.noteDao() // get acces to the DAO

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context : Context): NoteDatabase
    = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db")
            .fallbackToDestructiveMigration().build()

}