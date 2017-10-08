package kitttn.cmindtesttask.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kitttn
 */

const val API_KEY = "faf882033d724aecb1365fc7d1a448f3"

interface NewsApi {
    @GET("sources")
    fun getSources(): Observable<SourcesResp>

    @GET("articles")
    fun getArticles(@Query("source") sourceId: String, @Query("apiKey") apiKey: String = API_KEY): Observable<ArticlesResp>
}