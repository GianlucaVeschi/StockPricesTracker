package com.gianlucaveschi.stockpricestracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.stockpricestracker.R
import com.gianlucaveschi.stockpricestracker.databinding.MainFragmentBinding
import com.gianlucaveschi.stockpricestracker.domain.StockModel
import com.gianlucaveschi.stockpricestracker.domain.StockPriceModel
import com.gianlucaveschi.stockpricestracker.util.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val stockPricesAdapter by lazy { StockPricesAdapter(stockModelsList) }

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
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvStockPrices.adapter = stockPricesAdapter
        binding.rvStockPrices.setup()
    }

    fun RecyclerView.setup(removeDivider: Boolean = false, animateItems: Boolean = false) {
        layoutManager = LinearLayoutManager(context, animateItems)

        if (!removeDivider) {
            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.recyclerview_divider)!!)
            addItemDecoration(itemDecorator)
        }

    }

    companion object {
        val stockModelsList: List<StockPriceModel> = listOf(
            StockPriceModel(StockModel("Microsoft Corp.", "US5949181045"), 2103.toBigDecimal()),
            StockPriceModel(StockModel("Invesco Ltd.", "BMG491BT1088"), 473298.toBigDecimal()),
            StockPriceModel(StockModel("Apple Inc.", "US0378331005"), 21054233.toBigDecimal()),
            StockPriceModel(StockModel("Tesla Motors Inc.", "US88160R1014"), 321.toBigDecimal()),
            StockPriceModel(StockModel("Tiffany & Co.", "US8865471085"), 765.toBigDecimal()),
            StockPriceModel(StockModel("Amazon.com Inc.", "US0231351067"), 124.toBigDecimal()),
            StockPriceModel(StockModel("Nike, Inc.", "US6541061031"), 233.toBigDecimal()),
            StockPriceModel(StockModel("Kellogg Co.", "US4878361082"), 443.toBigDecimal()),
            StockPriceModel(StockModel("JPMorgan & Co.", "US46625H1005"), 22.toBigDecimal()),
            StockPriceModel(StockModel("Microsoft Corp.", "US5949181045"), 2103.toBigDecimal()),
            StockPriceModel(StockModel("Invesco Ltd.", "BMG491BT1088"), 473298.toBigDecimal()),
            StockPriceModel(StockModel("Apple Inc.", "US0378331005"), 21054233.toBigDecimal()),
            StockPriceModel(StockModel("Tesla Motors Inc.", "US88160R1014"), 321.toBigDecimal()),
            StockPriceModel(StockModel("Tiffany & Co.", "US8865471085"), 765.toBigDecimal()),
            StockPriceModel(StockModel("Amazon.com Inc.", "US0231351067"), 124.toBigDecimal()),
            StockPriceModel(StockModel("Nike, Inc.", "US6541061031"), 233.toBigDecimal()),
            StockPriceModel(StockModel("Kellogg Co.", "US4878361082"), 443.toBigDecimal()),
            StockPriceModel(StockModel("JPMorgan & Co.", "US46625H1005"), 22.toBigDecimal())
        )
    }

}