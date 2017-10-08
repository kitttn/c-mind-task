package kitttn.cmindtesttask.di.modules

import dagger.Module
import dagger.Provides
import kitttn.cmindtesttask.di.annotations.PerActivity
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.presenter.SourcesPresenter
import kitttn.cmindtesttask.views.MainActivity

/**
 * @author kitttn
 */

@PerActivity @Module
class FragmentModule(private val activity: MainActivity) {
    @Provides @PerActivity
    fun sourcesPresenter(api: NewsApi) = SourcesPresenter(api)

    @Provides @PerActivity
    fun activity() = activity
}