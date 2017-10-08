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

class SourcesInteractor(private val api: NewsApi,
                        private val subscribeScheduler: Scheduler = Schedulers.io(),
                        private val scheduler: Scheduler = AndroidSchedulers.mainThread()) {

    private val TAG = "SourcesInteractor"
    private val sourcesState = BehaviorSubject.create<SourcesState>()
    private val composite = CompositeDisposable()
    private val stateList = mutableListOf<SourcesState>()

    init {
        changeState(SourceStateNothing())
    }

    fun loadSources() {
        changeState(SourcesStateLoading())

        composite += Observable.just(true)
//                .delay(5, TimeUnit.SECONDS)
                .flatMap { api.getSources() }
                .subscribeOn(subscribeScheduler)
                .observeOn(scheduler)
                .map {
                    if (it.status != "ok")
                        SourcesStateError(Error())
                    else SourcesStateData(it.sources)
                }
                .subscribe(this::changeState, { changeState(SourcesStateError(it)) })
    }

    fun getStateChangeObservable(): Observable<SourcesState> = sourcesState

    private fun changeState(state: SourcesState) {
        //Log.i(TAG, "changeState: Current state: $state")
        stateList += state
        sourcesState.onNext(state)
    }
}