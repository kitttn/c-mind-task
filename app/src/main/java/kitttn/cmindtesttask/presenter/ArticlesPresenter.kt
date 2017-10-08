package kitttn.cmindtesttask.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kitttn.cmindtesttask.model.ArticleEntity
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.plusAssign

/**
 * @author kitttn
 */

class ArticlesPresenter(private val api: NewsApi) {
    private val composite = CompositeDisposable()

    fun stop() {
        composite.clear()
    }

    fun loadArticles(sourceId: String, success: (List<ArticleEntity>) -> Unit, error: (Throwable) -> Unit = Throwable::printStackTrace) {
        composite += api.getArticles(sourceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.articles }
                .subscribe(success, error)
    }
}