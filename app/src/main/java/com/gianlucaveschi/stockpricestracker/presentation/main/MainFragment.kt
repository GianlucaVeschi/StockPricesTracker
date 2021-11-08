package com.gianlucaveschi.stockpricestracker.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianlucaveschi.stockpricestracker.databinding.MainFragmentBinding
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val stockPricesAdapter = TickerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        collectTickerUpdates()
    }

    private fun initBinding() {
        binding.tickersListRecView.adapter = stockPricesAdapter
        binding.tickersListRecView.layoutManager = LinearLayoutManager(view?.context)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
    }

    private fun collectTickerUpdates(){
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.tickerStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED) //Avoid collecting flows when UI is in the background
                .onEach {
                    stockPricesAdapter.updateSpecificTicker(it)
                }.launchIn(lifecycleScope)
        }
    }
}