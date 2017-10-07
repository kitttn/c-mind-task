package kitttn.cmindtesttask.views.sources

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.SourceEntity
import kotlinx.android.synthetic.main.fragment_sources.*

/**
 * @author kitttn
 */

class SourcesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sources, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourcesRecyclerView.layoutManager = LinearLayoutManager(activity)
        sourcesRecyclerView.adapter = createTestAdapter()
    }

    private fun createTestAdapter(): SourceViewAdapter {
        val list = IntRange(1, 10).map { "Source $it" }.map { SourceEntity(it, it, it, getString(R.string.lorem_ipsum)) }
        return SourceViewAdapter(list)
    }
}