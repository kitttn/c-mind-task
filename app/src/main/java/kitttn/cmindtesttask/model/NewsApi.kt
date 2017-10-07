package kitttn.cmindtesttask.model

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author kitttn
 */

const val API_KEY = "faf882033d724aecb1365fc7d1a448f3"

interface NewsApi {
    @GET("sources")
    fun getSources(): Observable<SourcesResp>
}