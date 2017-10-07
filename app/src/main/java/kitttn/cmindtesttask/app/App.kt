package kitttn.cmindtesttask.app

import android.app.Application
import kitttn.cmindtesttask.di.components.AppComponent
import kitttn.cmindtesttask.di.components.DaggerAppComponent

/**
 * @author kitttn
 */

class App : Application() {
    companion object {
        val graph: AppComponent by lazy { DaggerAppComponent.create() }
    }

    override fun onCreate() {
        super.onCreate()
    }
}