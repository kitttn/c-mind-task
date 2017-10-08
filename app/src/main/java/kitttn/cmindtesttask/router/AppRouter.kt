package kitttn.cmindtesttask.router

import io.reactivex.disposables.CompositeDisposable
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.interactors.ArticlesInteractor
import kitttn.cmindtesttask.interactors.SourcesInteractor
import kitttn.cmindtesttask.plusAssign
import kitttn.cmindtesttask.states.ArticleStateOpenedNew
import kitttn.cmindtesttask.states.SourceStateNothing
import kitttn.cmindtesttask.views.MainActivity
import kitttn.cmindtesttask.views.articles.ArticlesFragment
import kitttn.cmindtesttask.views.sources.SourcesFragment

/**
 * @author kitttn
 */

class AppRouter(private val activity: MainActivity,
                private val articles: ArticlesInteractor,
                private val sources: SourcesInteractor) {

    companion object {
        const val ARTICLES = "articles"
        const val SOURCES = "sources"
    }

    private val composite = CompositeDisposable()

    fun start() {
        composite += articles.getStateChangeObservable()
                .subscribe({
                    if (it is ArticleStateOpenedNew)
                        openArticlesFragment(it.sourceId)
                })

        composite += sources.getStateChangeObservable()
                .subscribe({
                    if (it is SourceStateNothing)
                        openSourcesFragment()
                })
    }

    fun stop() {
        composite.clear()
    }

    fun openArticlesPage(sourceId: String) {
        articles.openPage(sourceId)
    }

    private fun openArticlesFragment(sourceId: String) {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, ArticlesFragment.newInstance(sourceId), ARTICLES)
                .addToBackStack(ARTICLES)
                .commit()
    }

    private fun openSourcesFragment() {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, SourcesFragment(), SOURCES)
                .addToBackStack(SOURCES)
                .commit()
    }
}