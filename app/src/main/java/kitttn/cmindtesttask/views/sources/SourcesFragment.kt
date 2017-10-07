package kitttn.cmindtesttask.views.sources

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.SourceEntity
import kitttn.cmindtesttask.presenter.SourcesPresenter
import kitttn.cmindtesttask.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sources.*
import javax.inject.Inject

/**
 * @author kitttn
 */

class SourcesFragment : BaseFragment() {
    @Inject lateinit var presenter: SourcesPresenter
    private val dataList = mutableListOf<SourceEntity>()
    private val adapter = SourceViewAdapter(dataList)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sources, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        act.component.inject(this)

        sourcesRecyclerView.layoutManager = LinearLayoutManager(activity)
        sourcesRecyclerView.adapter = adapter

        refresher.setOnRefreshListener { loadSources() }

        loadSources()
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
        return SourceViewAdapter(list)
    }
}