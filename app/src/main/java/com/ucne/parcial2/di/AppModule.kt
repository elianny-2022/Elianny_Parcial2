package com.ucne.parcial2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.repository.Ticket3Repository
import com.ucne.parcial2.data.repository.TicketRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract  fun bindTicketRepository(impl: TicketRepositoryImp): Ticket3Repository

}
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesTePrestoApi(moshi: Moshi): TicketsApi {
        return Retrofit.Builder()
            .baseUrl("https://teprestoapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketsApi::class.java)
    }

}