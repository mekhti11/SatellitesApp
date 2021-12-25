package tr.com.mekhti.satellitesapp.data.data_source

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tr.com.mekhti.satellitesapp.data.model.Satellite
import tr.com.mekhti.satellitesapp.utils.JsonFileNames
import java.io.IOException

class ReadJSON (var context: Context) {


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

        val gson = Gson()
        val satellitesType = object : TypeToken<ArrayList<Satellite>>() {}.type

        return gson.fromJson(jsonString, satellitesType);
    }

}