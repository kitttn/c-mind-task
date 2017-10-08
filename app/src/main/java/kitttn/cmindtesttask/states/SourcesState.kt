package kitttn.cmindtesttask.states

import kitttn.cmindtesttask.model.SourceEntity

/**
 * @author kitttn
 */

sealed class SourcesState

class SourcesStateLoading : SourcesState()

data class SourcesStateError(val error: Throwable) : SourcesState()

data class SourcesStateData(val data: List<SourceEntity>) : SourcesState()
