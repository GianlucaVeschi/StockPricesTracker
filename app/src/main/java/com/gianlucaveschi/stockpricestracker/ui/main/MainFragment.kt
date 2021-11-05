package com.gianlucaveschi.stockpricestracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.viewModels
import com.gianlucaveschi.stockpricestracker.databinding.MainFragmentBinding
import com.gianlucaveschi.stockpricestracker.ui.util.setup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val stockPricesAdapter by lazy { StockPricesAdapter(mainViewModel.tickersListStateFlow.value) }

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

    private fun onNewDataAvailable(@Suppress("UNUSED_PARAMETER") unit: Unit) {
        stockPricesAdapter.notifyDataSetChanged()
    }
}