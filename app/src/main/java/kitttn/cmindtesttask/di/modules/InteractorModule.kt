package kitttn.cmindtesttask.di.modules

import dagger.Module
import dagger.Provides
import kitttn.cmindtesttask.interactors.SourcesInteractor
import kitttn.cmindtesttask.model.NewsApi
import javax.inject.Singleton

/**
 * @author kitttn
 */

@Module @Singleton
class InteractorModule {
    @Provides @Singleton
    fun provideSourcesInteractor(api: NewsApi) = SourcesInteractor(api)
}