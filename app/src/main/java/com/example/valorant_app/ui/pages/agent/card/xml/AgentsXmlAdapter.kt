package com.example.valorant_app.ui.pages.agent.card.xml

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.databinding.ItemAgentBinding

class AgentsXmlAdapter(
    private val onClick: (AgentCard) -> Unit
) : ListAdapter<AgentCard, AgentsXmlAdapter.AgentVH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentVH {
        val binding = ItemAgentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AgentVH(binding)
    }

    override fun onBindViewHolder(holder: AgentVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AgentVH(private val b: ItemAgentBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(agent: AgentCard) {
            b.tvName.text = agent.displayName

            b.ivAgent.load(agent.displayIconSmall) {
                placeholder(R.drawable.progress_indeterminate_horizontal)
                error(R.drawable.stat_notify_error)
            }

            val tags = agent.characterTags
                .filterNot { it.isNullOrBlank() }
                .joinToString(" • ")
            b.tvDescription.text = tags.ifBlank { b.root.context.getString(com.example.valorant_app.R.string.no_tags_informed) }

            b.root.setOnClickListener { onClick(agent) }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<AgentCard>() {
            override fun areItemsTheSame(old: AgentCard, new: AgentCard) =
                old.uuid == new.uuid

            override fun areContentsTheSame(old: AgentCard, new: AgentCard) =
                old == new
        }
    }
}