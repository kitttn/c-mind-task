package kitttn.cmindtesttask.presenter

import io.reactivex.disposables.CompositeDisposable
import kitttn.cmindtesttask.interactors.ArticlesInteractor
import kitttn.cmindtesttask.plusAssign
import kitttn.cmindtesttask.states.ArticleStateNothing
import kitttn.cmindtesttask.views.articles.ArticleView

/**
 * @author kitttn
 */

class ArticlesPresenter(private val interactor: ArticlesInteractor) {
    private val composite = CompositeDisposable()
    var sourceId: String = ""
    var view: ArticleView? = null

    fun start() {
        composite += interactor.getStateChangeObservable()
                .doOnNext { if (it is ArticleStateNothing) loadArticles() }
                .subscribe({ view?.render(it) })
    }

    fun stop() {
        composite.clear()
    }

    fun loadArticles() {
        interactor.loadArticles(sourceId)
    }
}