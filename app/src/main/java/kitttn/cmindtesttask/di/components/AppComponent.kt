package kitttn.cmindtesttask.di.components

import dagger.Component
import kitttn.cmindtesttask.di.modules.AppModule
import kitttn.cmindtesttask.model.NewsApi
import javax.inject.Singleton

/**
 * @author kitttn
 */

@Singleton @Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    // inherited fields
    var api: NewsApi
}