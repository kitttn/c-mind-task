package kitttn.cmindtesttask.model

/**
 * @author kitttn
 */

data class SourceEntity(val id: String, val name: String, val url: String, val description: String)

data class ArticleEntity(val author: String, val description: String, val title: String, val url: String,
                         val urlToImage: String, val publishedAt: String)

data class SourcesResp(val status: String, val sources: List<SourceEntity>)

data class ArticlesResp(val status: String, val articles: List<ArticleEntity>)