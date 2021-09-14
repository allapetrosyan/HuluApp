package huluapplication.huluapplication.huluapplication.repo

import huluapplication.huluapplication.huluapplication.app_result.AppResult
import huluapplication.huluapplication.huluapplication.model.FilmGlobalData

interface Repository {
    suspend fun getAllImages() : AppResult<List<FilmGlobalData>>
}