package com.example.valorant_app.ui.pages.agent.card.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.databinding.FragmentAgentsXmlBinding
import com.example.valorant_app.ui.pages.agent.card.compose.AgentCardUiState
import com.example.valorant_app.ui.pages.agent.card.compose.AgentScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentsXmlFragment : Fragment() {
    var onAgentSelected: ((String) -> Unit)? = null

    private var _binding: FragmentAgentsXmlBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AgentScreenViewModel by viewModels()
    private lateinit var adapter: AgentsXmlAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsXmlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbarAgents.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        adapter = AgentsXmlAdapter { agent: AgentCard ->
            onAgentSelected?.invoke(agent.uuid)
        }
        binding.rvAgents.adapter = adapter

        val chipGroup = binding.chipGroup
        val selectedTags = mutableSetOf<String>()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                if (state is AgentCardUiState.Success) {
                    val agents = state.agents
                    adapter.submitList(state.agents)

                    val allTags = agents
                        .flatMap { it.characterTags.orEmpty().filterNot { it.isNullOrBlank() } }
                        .distinct()

                    chipGroup.removeAllViews()
                    allTags.forEach { tags ->
                        val chip = com.google.android.material.chip.Chip(requireContext()).apply {
                            text = tags
                            isCheckable = true
                            setOnCheckedChangeListener { _, isChecked ->
                                if (isChecked) selectedTags.add(
                                    tags ?: ""
                                ) else selectedTags.remove(tags)
                                val filtered = if (selectedTags.isEmpty()) agents
                                else agents.filter {
                                    it.characterTags.orEmpty().any { tag ->
                                        selectedTags.contains(tag)
                                    }
                                }
                                adapter.submitList(filtered)
                            }
                        }
                        chipGroup.addView(chip)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}