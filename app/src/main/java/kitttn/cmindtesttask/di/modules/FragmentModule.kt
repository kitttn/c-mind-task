package kitttn.cmindtesttask.di.modules

import dagger.Module
import dagger.Provides
import kitttn.cmindtesttask.di.annotations.PerActivity
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.presenter.SourcesPresenter

/**
 * @author kitttn
 */

@PerActivity @Module
class FragmentModule {
    @Provides @PerActivity
    fun sourcesPresenter(api: NewsApi) = SourcesPresenter(api)
}