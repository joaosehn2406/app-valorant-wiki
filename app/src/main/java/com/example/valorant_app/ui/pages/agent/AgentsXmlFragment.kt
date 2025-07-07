package com.example.valorant_app.ui.pages.agent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.databinding.FragmentAgentsXmlBinding
import com.example.valorant_app.ui.pages.agent.card.AgentCardUiState
import com.example.valorant_app.ui.pages.agent.card.AgentScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentsXmlFragment : Fragment() {

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
            findNavController().navigate("AgentSingleRoute/${agent.uuid}")
        }
        binding.rvAgents.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                if (state is AgentCardUiState.Success) {
                    adapter.submitList(state.agents)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
