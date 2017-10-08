package kitttn.cmindtesttask.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.plusAssign
import kitttn.cmindtesttask.states.SourcesStateData
import kitttn.cmindtesttask.states.SourcesStateError
import kitttn.cmindtesttask.states.SourcesStateLoading
import kitttn.cmindtesttask.views.sources.SourcesView

/**
 * @author kitttn
 */

class SourcesPresenter(private val api: NewsApi) {
    private val TAG = "SourcesPresenter"
    private val composite = CompositeDisposable()
    var view: SourcesView? = null

    fun start() {

    }

    fun stop() {
        composite.clear()
        Log.i(TAG, "stop: Subscriptions are cleared!")
    }

    fun loadSources() {
        composite += api.getSources()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { view?.render(SourcesStateLoading()) }
                .map { it.sources }
                .map { SourcesStateData(it) }
                .subscribe({ view?.render(it) }, { view?.render(SourcesStateError(it)) })
    }
}