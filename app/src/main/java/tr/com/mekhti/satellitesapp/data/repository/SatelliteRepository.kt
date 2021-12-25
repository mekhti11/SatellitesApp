package tr.com.mekhti.satellitesapp.data.repository

import tr.com.mekhti.satellitesapp.data.data_source.ReadJSON
import tr.com.mekhti.satellitesapp.data.model.Satellite

class SatelliteRepository(var readJSON : ReadJSON) {

    fun getSatellites():ArrayList<Satellite>{
        return readJSON.getSatellites()
    }

}