package com.candidate.android.dev.wongnai_assignment.Adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.CoinX
import com.candidate.android.dev.wongnai_assignment.Extension.loadFromURLSVG
import com.candidate.android.dev.wongnai_assignment.R
import kotlinx.android.synthetic.main.adapter_coin.view.*

enum class CoinViewType(val type: Int) {
    NORMAL(0),
    MOD(1)
}

class CoinAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewType: Int? = null
    private var coinData = ArrayList<CoinX>()

    override fun getItemViewType(position: Int): Int {
        viewType = if ((position + 1) % 5 == 0) 1 else 0
        return viewType ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CoinViewType.MOD.type -> {
                ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_coin_mod_five, parent, false)
                )
            }
            else -> {
                ViewHolder2(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_coin, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return coinData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        coinData[position].let {
            when (viewType) {
                CoinViewType.NORMAL.type -> {
                    val h = holder as? ViewHolder
                    h?.bindData(it)
                }
                CoinViewType.MOD.type ->{
                    val h = holder as? ViewHolder2
                    h?.bindModFiveData(it)
                }
                else -> {}
            }
        }
    }

    fun setCoinData(firstSearch: Boolean? = null, coinData: ArrayList<CoinX>) {
        when (firstSearch) {
            true -> {
                this.coinData.clear()
                this.coinData.addAll(coinData)
                notifyDataSetChanged()
            }
            false -> {
                this.coinData.addAll(coinData)
                notifyDataSetChanged()
            }
            null -> {
                this.coinData.clear()
                this.coinData.addAll(coinData)
                notifyDataSetChanged()
            }
        }
    }


}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(data: CoinX) {
        itemView.let { v ->
            data.iconUrl?.let { url -> v.logoCoin.loadFromURLSVG(url) }
            data.name?.let { name -> v.coinTitle.text = name }
            data.description?.let { desc -> v.coinDetail.text = Html.fromHtml(desc) }
        }
    }

    fun bindModFiveData(data: CoinX) {
        itemView.let { v ->
            data.iconUrl?.let { url -> v.logoCoin.loadFromURLSVG(url) }
            data.name?.let { name -> v.coinTitle.text = name }
        }
    }
}

class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(data: CoinX) {
        itemView.let { v ->
            data.iconUrl?.let { url -> v.logoCoin.loadFromURLSVG(url) }
            data.name?.let { name -> v.coinTitle.text = name }
            data.description?.let { desc -> v.coinDetail.text = Html.fromHtml(desc) }
        }
    }

    fun bindModFiveData(data: CoinX) {
        itemView.let { v ->
            data.iconUrl?.let { url -> v.logoCoin.loadFromURLSVG(url) }
            data.name?.let { name -> v.coinTitle.text = name }
        }
    }
}


