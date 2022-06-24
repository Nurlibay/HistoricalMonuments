package uz.texnopos.historicalmonuments.domain

import uz.texnopos.historicalmonuments.data.entity.Monument

interface MainRepository {

    suspend fun getLifeData(): List<Monument>
}