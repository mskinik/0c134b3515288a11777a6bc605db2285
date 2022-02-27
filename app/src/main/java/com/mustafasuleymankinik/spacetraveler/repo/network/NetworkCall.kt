package com.mustafasuleymankinik.spacetraveler.repo.network

import com.mustafasuleymankinik.spacetraveler.model.Planet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
class NetworkCall {
    companion object {
        fun getList(
            observer: GenericObserver<List<Planet>>
        ) {
            NetworkService.getApi().getList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : GenericObserver<List<Planet>>() {
                    override fun onNext(t: List<Planet>) {
                        observer.onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        observer.onError(e)
                    }
                })
        }
    }
}