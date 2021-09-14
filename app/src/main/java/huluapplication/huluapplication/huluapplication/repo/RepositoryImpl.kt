package huluapplication.huluapplication.huluapplication.repo

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import huluapplication.huluapplication.huluapplication.app_result.AppResult
import huluapplication.huluapplication.huluapplication.app_result.handleApiError
import huluapplication.huluapplication.huluapplication.app_result.handleSuccess
import huluapplication.huluapplication.huluapplication.model.FilmGlobalData
import huluapplication.huluapplication.huluapplication.network.ApiInterface

class RepositoryImpl(
    private val api: ApiInterface
) : Repository {

    override suspend fun getAllImages(): AppResult<List<FilmGlobalData>> {
        return try {
            val response = api.getDataFromApi()
            // val response = api.getDataFromApi()
            if (response.isSuccessful) {
                response.body()?.let {

                    handleSuccess(it.datas)
                } ?: run {
                    handleApiError("error")
                }
            } else {
                handleApiError("error")
            }
        } catch (e: Exception) {
            Log.d("dwd", "error")
            AppResult.Error(e)
        }


    }


    private fun isOnline(context: Context): Boolean {
        var bool = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE)
        if (connectivityManager is ConnectivityManager){
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            bool = activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
        return bool
    }
}