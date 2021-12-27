package tr.com.mekhti.satellitesapp.data.data_source

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tr.com.mekhti.satellitesapp.data.model.JSONPositionsList
import tr.com.mekhti.satellitesapp.data.model.PositionsOfSatellite
import tr.com.mekhti.satellitesapp.data.model.Satellite
import tr.com.mekhti.satellitesapp.data.model.SatelliteDetail
import tr.com.mekhti.satellitesapp.utils.JsonFileNames
import java.io.IOException

class ReadJSON (var context: Context) {

    private val gson = Gson()


    fun getJsonDataFromAsset( fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


    fun getSatellites(): ArrayList<Satellite> {

        val jsonString = getJsonDataFromAsset(JsonFileNames.SATELLITE_LIST)


        val satellitesType = object : TypeToken<ArrayList<Satellite>>() {}.type

        return gson.fromJson(jsonString, satellitesType);
    }


    fun getSatelliteDetails(): ArrayList<SatelliteDetail> {

        val jsonString = getJsonDataFromAsset(JsonFileNames.SATELLITE_DETAIL)

        val satelliteDetailType = object : TypeToken<ArrayList<SatelliteDetail>>() {}.type

        return gson.fromJson(jsonString, satelliteDetailType)
    }

    fun getSatellitePositions(): ArrayList<PositionsOfSatellite> {

        val jsonString = getJsonDataFromAsset(JsonFileNames.POSITIONS)

        val satellitePositionType = object : TypeToken<JSONPositionsList>() {}.type

        val positions : JSONPositionsList = gson.fromJson(jsonString, satellitePositionType)
        return positions.list
    }

}