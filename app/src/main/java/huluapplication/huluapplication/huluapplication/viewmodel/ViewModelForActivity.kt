package huluapplication.huluapplication.huluapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import huluapplication.huluapplication.huluapplication.app_result.AppResult
import huluapplication.huluapplication.huluapplication.model.FilmGlobalData
import huluapplication.huluapplication.huluapplication.repo.Repository
import kotlinx.coroutines.launch

class ViewModelForActivity (private var repository: Repository) : ViewModel() {

    val filmList = MutableLiveData<List<FilmGlobalData>>()
    fun getMutableLiveData() {
        viewModelScope.launch() {
            when (val result = repository.getAllImages()) {
                is AppResult.Success -> {
                    filmList.value = result.successData!!
                }
                is AppResult.Error -> Log.d("dwd", "error")
            }
        }
    }
}