package com.gianlucaveschi.stockpricestracker.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.stockpricestracker.databinding.ItemViewTickerBinding
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private var tickersList: List<TickerUiModel> = listOf()

    fun setTickersList(tickersList : List<TickerUiModel>) {
        this.tickersList = tickersList
        notifyDataSetChanged()
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