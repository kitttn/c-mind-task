package kitttn.cmindtesttask.views.sources

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.SourceEntity
import kitttn.cmindtesttask.presenter.SourcesPresenter
import kitttn.cmindtesttask.router.AppRouter
import kitttn.cmindtesttask.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_data_page.*
import javax.inject.Inject

/**
 * @author kitttn
 */

class SourcesFragment : BaseFragment() {
    private val TAG = "SourcesFragment"
    private val dataList = mutableListOf<SourceEntity>()
    private val adapter by lazy { SourceViewAdapter(dataList, router) }

    @Inject lateinit var presenter: SourcesPresenter
    @Inject lateinit var router: AppRouter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_data_page, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated: Component: ${act.component}")
        act.component.inject(this)

        dataRV.layoutManager = LinearLayoutManager(activity)
        dataRV.adapter = adapter

        refresher.setOnRefreshListener { loadSources() }

        loadSources()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    private fun loadSources() {
        refresher.isRefreshing = true

        presenter.loadSources({
            refresher.isRefreshing = false
            dataList.clear()
            dataList += it
            adapter.notifyDataSetChanged()
        })
    }

    private fun createTestAdapter(): SourceViewAdapter {
        val list = IntRange(1, 10).map { "Source $it" }.map { SourceEntity(it, it, it, getString(R.string.lorem_ipsum)) }
        return SourceViewAdapter(list, router)
    }
}