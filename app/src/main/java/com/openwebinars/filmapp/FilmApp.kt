package com.openwebinars.filmapp

import android.app.Application
import androidx.room.Room
import com.openwebinars.filmapp.data.database.FilmDb
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FilmApp : Application()