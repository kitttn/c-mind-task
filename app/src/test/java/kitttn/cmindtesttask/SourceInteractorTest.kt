package kitttn.cmindtesttask

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import kitttn.cmindtesttask.interactors.SourcesInteractor
import kitttn.cmindtesttask.model.NewsApi
import kitttn.cmindtesttask.model.SourceEntity
import kitttn.cmindtesttask.model.SourcesResp
import kitttn.cmindtesttask.states.SourceStateNothing
import kitttn.cmindtesttask.states.SourcesStateData
import kitttn.cmindtesttask.states.SourcesStateError
import kitttn.cmindtesttask.states.SourcesStateLoading
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.concurrent.TimeUnit

/**
 * @author kitttn
 */

fun createTestDataList() = (1..20).map { SourceEntity("$it", "Source $it", "", "") }

object SourceInteractorTest : Spek({
    given("Source interactor") {
        val api = mock(NewsApi::class.java)
        `when`(api.getSources())
                .thenReturn(Observable.just(SourcesResp("ok", createTestDataList())))
                .thenReturn(Observable.just(SourcesResp("error", emptyList())))

        val scheduler = TestScheduler()
        val interactor = SourcesInteractor(api, scheduler, Schedulers.trampoline())

        on("First subscription to state observable") {
            val result = interactor.getStateChangeObservable().test()

            it("Should return Nothing state") {
                result.assertNotComplete()
                val values = result.values()
                assertEquals(1, values.size)
                val element = values[0]
                assert(element is SourceStateNothing)
            }
        }

        on("Requesting the data from API first time") {
            val testing = interactor.getStateChangeObservable().test()
            interactor.loadSources()
            scheduler.advanceTimeTo(5, TimeUnit.SECONDS)

            it("should change the state from Nothing -> Loading -> Data") {
                testing.assertNotComplete()
                assertEquals(3, testing.values().size)
                val values = testing.values()
                assert(values[0] is SourceStateNothing)
                assert(values[1] is SourcesStateLoading)
                assert(values[2] is SourcesStateData)
                val list = values[2] as SourcesStateData
                assert(list.data.size == createTestDataList().size)
            }
        }

        on("Requesting the data from API second time") {
            val testing = interactor.getStateChangeObservable().test()
            interactor.loadSources()
            scheduler.advanceTimeTo(5, TimeUnit.SECONDS)
            interactor.loadSources()
            scheduler.advanceTimeTo(7, TimeUnit.SECONDS)

            it("should change the state from Loading -> Error after success") {
                testing.assertNotComplete()
                assertEquals(5, testing.values().size)
                val values = testing.values()
                assert(values[0] is SourceStateNothing)
                assert(values[1] is SourcesStateLoading)
                assert(values[2] is SourcesStateData)
                assert(values[3] is SourcesStateLoading)
                assert(values[4] is SourcesStateError)
                val res = values[4] as SourcesStateError
                assert(res.error is Error)
            }
        }
    }
})