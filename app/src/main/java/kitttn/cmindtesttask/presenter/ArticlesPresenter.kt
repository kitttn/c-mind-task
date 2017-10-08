package kitttn.cmindtesttask.presenter

import io.reactivex.disposables.CompositeDisposable
import kitttn.cmindtesttask.interactors.ArticlesInteractor
import kitttn.cmindtesttask.plusAssign
import kitttn.cmindtesttask.views.articles.ArticleView

/**
 * @author kitttn
 */

class ArticlesPresenter(private val interactor: ArticlesInteractor) {
    private val composite = CompositeDisposable()
    var view: ArticleView? = null

    fun start() {
        composite += interactor.getStateChangeObservable()
                .subscribe({ view?.render(it) })
    }

    fun stop() {
        composite.clear()
    }

    fun loadArticles(sourceId: String) {
        interactor.loadArticles(sourceId)
    }
}