package kitttn.cmindtesttask.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.model.SourceEntity
import kitttn.cmindtesttask.plusAssign

/**
 * @author kitttn
 */

class SourcesPresenter(private val api: NewsApi) {
    private val composite = CompositeDisposable()

    fun start() {

    }

    fun stop() {
        composite.clear()
    }

    fun loadSources(success: (List<SourceEntity>) -> Unit, error: (Throwable) -> Unit = Throwable::printStackTrace) {
        composite += api.getSources()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { it.sources }
                .subscribe(success, error)
    }
}