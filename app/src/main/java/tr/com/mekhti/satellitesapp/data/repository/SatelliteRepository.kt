package tr.com.mekhti.satellitesapp.data.repository

import tr.com.mekhti.satellitesapp.data.data_source.ReadJSON
import tr.com.mekhti.satellitesapp.data.model.PositionsOfSatellite
import tr.com.mekhti.satellitesapp.data.model.Satellite
import tr.com.mekhti.satellitesapp.data.model.SatelliteDetail

class SatelliteRepository(var readJSON : ReadJSON) {

    fun getSatellites():ArrayList<Satellite>{
        return readJSON.getSatellites()
    }

    fun getSatelliteDetails() : ArrayList<SatelliteDetail>{
        return readJSON.getSatelliteDetails()
    }

    fun getSatellitePositions(): ArrayList<PositionsOfSatellite> {
        return readJSON.getSatellitePositions()
    }

}