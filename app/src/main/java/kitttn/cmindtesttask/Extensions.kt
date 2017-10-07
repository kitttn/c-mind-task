package kitttn.cmindtesttask

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author kitttn
 */

operator fun CompositeDisposable.plusAssign(other: Disposable) {
    this.add(other)
}