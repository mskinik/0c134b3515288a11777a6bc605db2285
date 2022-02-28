package com.mustafasuleymankinik.spacetraveler.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mustafasuleymankinik.spacetraveler.model.Planet
import com.mustafasuleymankinik.spacetraveler.model.User
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.repo.database.Database
import com.mustafasuleymankinik.spacetraveler.repo.network.GenericObserver
import com.mustafasuleymankinik.spacetraveler.repo.network.NetworkCall
import kotlin.math.sqrt

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
class MainFragmentViewModel(val app: Application) : AndroidViewModel(app) {
    var dao: Dao = Database.getInstance(app).noteDao()
    val planetList: LiveData<List<Planet>> = dao.getPlanetList()
    val user: LiveData<User> = dao.getUser()
    val mappedUserLiveData = Transformations.map(user) { usr->
        val shipDamage = usr.shipDamage
        val ugs = usr.ugs
        val ds = usr.ds
        val eus = usr.eus
        if (ugs == null || shipDamage == null || ds == null || eus == null) {
            user.value?.userCoordinateY = 0.0
            user.value?.userCoordinateX = 0.0
            user.value?.name = "Han Solo"
            user.value?.userPlanetName = "Dünya"
            return@map true
        } else if (shipDamage <= 0 || ugs <= 0 || ds <= 0 || eus <= 0){
                user.value?.userCoordinateY = 0.0
                user.value?.userCoordinateX = 0.0
                user.value?.name = "Han Solo"
                user.value?.userPlanetName = "Dünya"
                return@map true
            }
            else
                return@map false

    }

    init {
        dao.addUser(User())
        //val list = dao.getPlanetList()
        if (planetList.value.isNullOrEmpty()) {
            NetworkCall.getList(object : GenericObserver<List<Planet>>() {
                override fun onNext(t: List<Planet>) {
                    t.forEach { planet ->
                        planet.distanceToEarth =
                            calculateToEarth(planet.coordinateX, planet.coordinateY)
                        val a = dao.addPlanet(planet)
                    }
                    // _planetList.value = dao.getPlanetList()
                }

                override fun onError(e: Throwable) {
                    // Do nothing for now
                }
            })
        } /*else {
            list.let {
                _planetList.value = it
            }
        } */
    }

    private fun calculateToEarth(coordinateX: Double?, coordinateY: Double?): Double? {
        val x = coordinateX
        val y = coordinateY
        var distance: Double? = null
        if (x != null && y != null) {
            return sqrt((x * x) + (y * y))
        }
        return null
    }

    fun updatePlanetFavorite(id: Long, favorite: Boolean) {
        dao.updateFavorite(id, !favorite)
        // _planetList.value = dao.getPlanetList()
    }

    fun travel(id: Long, planet: Planet) {
        var finalUserUgs: Int? = null
        var azalacakEus: Int? = null
        var finalUserEus: Int? = null
        var azalacakDs: Int? = null
        var finalUserDs: Int? = null
        var range: Double? = null
        val xResult: Double?
        val yResult: Double?
        val userInfo = user.value
        if (userInfo != null) {
            val x1 = userInfo.userCoordinateX
            val y1 = userInfo.userCoordinateY
            if (x1 != null && y1 != null) {
                xResult = (planet.coordinateX?.minus(x1))
                yResult = (planet.coordinateY?.minus(y1))
                if (xResult != null && yResult != null) {
                    range = sqrt((xResult * xResult) + (yResult * yResult))
                }
            }
            range?.let {
                azalacakEus = range.toInt()
                azalacakEus?.let {
                    azalacakDs = it * 1000
                }
            }
            finalUserEus = azalacakEus?.let { userInfo.eus?.minus(it) }
            finalUserDs = azalacakDs?.let { userInfo.ds?.minus(it) }


            val userInfoUgs = userInfo.ugs
            if (userInfoUgs != null) {
                val planetNeed = planet.need
                if (planetNeed != null && userInfoUgs > planetNeed) {
                    finalUserUgs = planet.need?.let { userInfo.ugs?.minus(it) }
                    planet.need = 0
                    planet.stock = planet.capacity

                }
            }
            val user = user.value
            user?.eus = finalUserEus
            user?.ugs = finalUserUgs
            user?.ds = finalUserDs
            user?.shipDamage = user?.shipDamage?.minus(10)
            user?.userPlanetName = planet.name
            user?.userCoordinateX = planet.coordinateX
            user?.userCoordinateY = planet.coordinateY

            if (user != null) {
                val a = dao.updateUser(user)
            }
            val planetStock = planet.stock
            val planetNeed = planet.need
            if (planetStock != null && planetNeed != null) {

                dao.updatePlanetInfo(id, planetNeed, planetStock, true)
            }

        }
    }
}