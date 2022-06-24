package uz.texnopos.historicalmonuments.presenter.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.historicalmonuments.data.entity.Monument
import uz.texnopos.historicalmonuments.domain.MainRepository
import uz.texnopos.historicalmonuments.utils.Resource

class SearchViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _songs: MutableLiveData<Resource<List<Monument>>> = MutableLiveData()
    val songs: LiveData<Resource<List<Monument>>> get() = _songs

    fun getAllMonuments() {
        _songs.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getAllMonuments()
                _songs.value = Resource.success(result)
            } catch (e: Exception) {
                _songs.value = Resource.error(e.message.toString())
            }
        }
    }
}