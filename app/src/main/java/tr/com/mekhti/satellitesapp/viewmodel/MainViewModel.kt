package tr.com.mekhti.satellitesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tr.com.mekhti.satellitesapp.data.model.PositionsOfSatellite
import tr.com.mekhti.satellitesapp.data.repository.SatelliteRepository
import tr.com.mekhti.satellitesapp.data.model.Satellite
import tr.com.mekhti.satellitesapp.data.model.SatelliteDetail
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository : SatelliteRepository
) :ViewModel() {

    var satellites : MutableLiveData<ArrayList<Satellite>> = MutableLiveData()
    var details : MutableLiveData<ArrayList<SatelliteDetail>> = MutableLiveData()
    var positions : MutableLiveData<ArrayList<PositionsOfSatellite>> = MutableLiveData()

    var currentSatelliteDetail : MutableLiveData<SatelliteDetail> = MutableLiveData()
    var currentSatellite : MutableLiveData<Satellite> = MutableLiveData()
    var currentSatellitePosition : MutableLiveData<PositionsOfSatellite> = MutableLiveData()

    init {
        satellites.value = repository.getSatellites()
        details.value = repository.getSatelliteDetails()
        positions.value = repository.getSatellitePositions()
    }

    fun getSatelliteDetail(id : Int){
        currentSatellite.value = satellites.value?.find { it.id == id } as Satellite

        currentSatelliteDetail.value = details.value?.find { it.id == id } as SatelliteDetail

        currentSatellitePosition.value = positions.value?.find { it.id == id } as PositionsOfSatellite
    }

}