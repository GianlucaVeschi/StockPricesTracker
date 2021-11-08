package com.gianlucaveschi.stockpricestracker.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.stockpricestracker.databinding.ItemViewTickerBinding
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import timber.log.Timber

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private var tickersList: MutableList<TickerUiModel> = getHardcodedTickerUiModel()

    fun updateSpecificTicker(tickerUiModel: TickerUiModel) {
        val indexOfTicket = tickersList.updateTicker(tickerUiModel)
        notifyItemChanged(indexOfTicket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder(
            ItemViewTickerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = tickersList.size

    private fun getItem(position: Int) = tickersList[position]

    inner class TickerViewHolder(
        private val binding: ItemViewTickerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tickerUiModel: TickerUiModel) {
            binding.apply {
                ticker = tickerUiModel
                executePendingBindings()
            }
        }
    }
}

private fun MutableList<TickerUiModel>.updateTicker(ticker: TickerUiModel) : Int {
    val indexOfTicket = this.getTickerIndex(ticker)
    this[indexOfTicket] = ticker
    return indexOfTicket
}

private fun MutableList<TickerUiModel>.getTickerIndex(ticker: TickerUiModel): Int {
    return this.indexOfFirst { it.isin == ticker.isin }
}