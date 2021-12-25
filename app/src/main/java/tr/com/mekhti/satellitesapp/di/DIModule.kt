package tr.com.mekhti.satellitesapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tr.com.mekhti.satellitesapp.data.data_source.ReadJSON
import tr.com.mekhti.satellitesapp.data.repository.SatelliteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DIModule {

    @Provides
    @Singleton
    fun provideReadJSONDao(app: Application): ReadJSON {
        return ReadJSON(app)
    }


    @Provides
    @Singleton
    fun provideSatelliteRepository(readJSON: ReadJSON) : SatelliteRepository {
        return SatelliteRepository(readJSON)
    }
}