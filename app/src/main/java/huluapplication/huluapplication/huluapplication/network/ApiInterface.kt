package huluapplication.huluapplication.huluapplication.network

import huluapplication.huluapplication.huluapplication.model.FData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

@GET("https://firebasestorage.googleapis.com/v0/b/allatest-app.appspot.com/o/film_data.json?alt=media&token=b4252cae-e774-4f5b-830f-9545f8016de7")
suspend fun getDataFromApi(): Response<FData>
}