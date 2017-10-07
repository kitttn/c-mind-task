package kitttn.cmindtesttask.views.sources

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.SourceEntity

/**
 * @author kitttn
 */

class SourceViewAdapter(private val sources: List<SourceEntity>) : RecyclerView.Adapter<SourceViewHolder>() {
    override fun onBindViewHolder(holder: SourceViewHolder?, position: Int) {
        holder?.bind(sources[position])
    }

    override fun getItemCount() = sources.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SourceViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_source, parent, false)
        return SourceViewHolder(view)
    }

}

class SourceViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val header by lazy { view.findViewById<TextView?>(R.id.sourceViewHeader) }
    val description by lazy { view.findViewById<TextView?>(R.id.sourceViewDescription) }
    val link by lazy { view.findViewById<TextView?>(R.id.sourceViewLink) }

    fun bind(entity: SourceEntity) {
        header?.text = entity.name
        description?.text = entity.description
        link?.text = entity.url
    }
}