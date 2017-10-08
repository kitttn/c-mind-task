package kitttn.cmindtesttask.di.components

import dagger.Component
import kitttn.cmindtesttask.di.modules.AppModule
import kitttn.cmindtesttask.di.modules.InteractorModule
import kitttn.cmindtesttask.interactors.SourcesInteractor
import kitttn.cmindtesttask.model.NewsApi
import javax.inject.Singleton

/**
 * @author kitttn
 */

@Singleton @Component(modules = arrayOf(AppModule::class, InteractorModule::class))
interface AppComponent {
    // inherited fields
    var api: NewsApi
    var sources: SourcesInteractor
}