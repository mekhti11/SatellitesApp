package tr.com.mekhti.satellitesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tr.com.mekhti.satellitesapp.data.repository.SatelliteRepository
import tr.com.mekhti.satellitesapp.data.model.Satellite
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var repository : SatelliteRepository,
) :ViewModel() {

    var satellites : MutableLiveData<ArrayList<Satellite>> = MutableLiveData()

    init {
        satellites.value = repository.getSatellites()
    }

}