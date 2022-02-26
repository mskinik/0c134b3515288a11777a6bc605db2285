package com.mustafasuleymankinik.spacetraveler.repo.network

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
abstract class GenericObserver<T>: Observer<T> {
    override fun onSubscribe(d: Disposable) {
        // Do nothing
    }

    override fun onComplete() {
        // Do nothing
    }
}