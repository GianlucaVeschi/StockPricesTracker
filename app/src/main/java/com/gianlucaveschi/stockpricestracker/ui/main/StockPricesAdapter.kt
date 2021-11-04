package com.gianlucaveschi.stockpricestracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.stockpricestracker.databinding.ItemViewStockPriceBinding
import com.gianlucaveschi.stockpricestracker.domain.model.Ticker

class StockPricesAdapter(
    private val data: List<Ticker>
) : RecyclerView.Adapter<StockPricesAdapter.SPViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SPViewHolder {
        return SPViewHolder(ItemViewStockPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SPViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
        }
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int) = data[position]

    inner class SPViewHolder(private val binding: ItemViewStockPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ticker: Ticker) {
            binding.apply {
                stockModel = ticker
                executePendingBindings()
            }
        }
    }
}