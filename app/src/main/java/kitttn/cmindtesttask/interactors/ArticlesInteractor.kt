package kitttn.cmindtesttask.interactors

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.plusAssign
import kitttn.cmindtesttask.states.*

/**
 * @author kitttn
 */

class ArticlesInteractor(val api: NewsApi,
                         val observeScheduler: Scheduler = AndroidSchedulers.mainThread(),
                         val subscribe: Scheduler = Schedulers.io()) {

    private val articlesState = BehaviorSubject.create<ArticleState>()
    private val composite = CompositeDisposable()
    private val stateList = mutableListOf<ArticleState>()

    fun openPage(sourceId: String) {
        changeState(ArticleStateOpenedNew(sourceId))
    }

    fun loadArticles(sourceId: String) {
        changeState(ArticleStateLoading())

        composite += Observable.just(true)
//                .delay(5, TimeUnit.SECONDS)
                .flatMap { api.getArticles(sourceId) }
                .subscribeOn(subscribe)
                .observeOn(observeScheduler)
                .map {
                    if (it.status != "ok")
                        ArticleStateError(Error())
                    else ArticleStateData(it.articles)
                }
                .subscribe(this::changeState, { changeState(ArticleStateError(it)) })
    }

    fun getStateChangeObservable(): Observable<ArticleState> = articlesState

    private fun changeState(state: ArticleState) {
        //Log.i(TAG, "changeState: Current state: $state")
        stateList += state
        articlesState.onNext(state)
    }
}