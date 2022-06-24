package uz.texnopos.historicalmonuments.domain

import uz.texnopos.historicalmonuments.data.dao.MonumentDao
import uz.texnopos.historicalmonuments.data.entity.Monument

class MainRepositoryImpl constructor(private val dao: MonumentDao) : MainRepository {
    override suspend fun getAllMonuments(): List<Monument> {
        return dao.getAllMonuments()
    }

}