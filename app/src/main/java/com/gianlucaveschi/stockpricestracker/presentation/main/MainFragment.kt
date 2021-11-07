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
import com.gianlucaveschi.stockpricestracker.databinding.MainFragmentBinding
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestracker.presentation.util.setup
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlinx.coroutines.flow.collect

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
        observeViewModelCalls()
    }

    private fun initBinding() {
        binding.stockPricesRecView.adapter = stockPricesAdapter
        binding.stockPricesRecView.setup()
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
    }

    private fun observeViewModelCalls() {
        mainViewModel.newDataAvailable.observe(viewLifecycleOwner, ::onNewDataAvailable)
    }

    //Fix Me or Delete me, why is data only collected once at the beginning?
    private fun collectData(){
        lifecycleScope.launchWhenStarted {
            mainViewModel.tickersListStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED) //Avoid collecting flows when UI is in the background
                .collect {
                    Timber.d(" I FINALLY COLLECT $it")
                }
        }
    }

    private fun onNewDataAvailable(@Suppress("UNUSED_PARAMETER") unit: Unit) {
        stockPricesAdapter.setTickersList(mainViewModel.tickersListStateFlow.value)
    }
}