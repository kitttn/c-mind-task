package kitttn.cmindtesttask.presenter

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import kitttn.cmindtesttask.interactors.SourcesInteractor
import kitttn.cmindtesttask.plusAssign
import kitttn.cmindtesttask.states.SourceStateNothing
import kitttn.cmindtesttask.views.sources.SourcesView

/**
 * @author kitttn
 */

class SourcesPresenter(private val interactor: SourcesInteractor) {
    private val TAG = "SourcesPresenter"
    private val composite = CompositeDisposable()
    var view: SourcesView? = null

    fun start() {
        composite += interactor.getStateChangeObservable()
                .doOnNext { if (it is SourceStateNothing) loadSources() }
                .subscribe({ view?.render(it) })
    }

    fun stop() {
        composite.clear()
        Log.i(TAG, "stop: Subscriptions are cleared!")
    }

    fun loadSources() {
        interactor.loadSources()
    }
}