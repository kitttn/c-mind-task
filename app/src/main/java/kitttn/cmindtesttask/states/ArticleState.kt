package kitttn.cmindtesttask.states

import kitttn.cmindtesttask.model.ArticleEntity

/**
 * @author kitttn
 */

sealed class ArticleState

data class ArticleStateError(val error: Throwable) : ArticleState()

class ArticleStateLoading : ArticleState()

data class ArticleStateData(val articles: List<ArticleEntity>) : ArticleState()