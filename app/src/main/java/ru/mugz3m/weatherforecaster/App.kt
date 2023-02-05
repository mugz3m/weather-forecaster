package ru.mugz3m.weatherforecaster

import android.app.Application
import android.content.Context
import ru.mugz3m.weatherforecaster.ioc.ApplicationComponent

class App : Application() {

    val applicationComponent by lazy { ApplicationComponent(applicationContext) }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}
