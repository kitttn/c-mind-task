package kitttn.cmindtesttask.di.modules

import dagger.Module
import dagger.Provides
import kitttn.cmindtesttask.di.annotations.PerActivity
import kitttn.cmindtesttask.interactors.ArticlesInteractor
import kitttn.cmindtesttask.interactors.SourcesInteractor
import kitttn.cmindtesttask.presenter.ArticlesPresenter
import kitttn.cmindtesttask.presenter.SourcesPresenter
import kitttn.cmindtesttask.views.MainActivity

/**
 * @author kitttn
 */

@PerActivity @Module
class FragmentModule(private val activity: MainActivity) {
    @Provides @PerActivity
    fun sourcesPresenter(interactor: SourcesInteractor) = SourcesPresenter(interactor)

    @Provides @PerActivity
    fun articlesPresenter(interactor: ArticlesInteractor) = ArticlesPresenter(interactor)

    @Provides @PerActivity
    fun activity() = activity
}