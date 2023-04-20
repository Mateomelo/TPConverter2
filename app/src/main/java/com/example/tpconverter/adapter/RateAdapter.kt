package com.example.tpconverter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tpconverter.R
import com.example.tpconverter.model.Rate
import com.squareup.picasso.Picasso


class RateAdapter(
    private val context: Context,
    private val rates: List<Rate>
) : RecyclerView.Adapter<RateAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rateCode: TextView = view.findViewById(R.id.code_text_field)
        val rateLabel: TextView = view.findViewById(R.id.label_text_view)
        val rateRate: TextView = view.findViewById(R.id.rate_text_view)
        val rateFlag: ImageView = view.findViewById(R.id.flag_view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_rate_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val rate = rates[position]
        holder.rateCode.text = rate.code
        holder.rateLabel.text = rate.label
        holder.rateRate.text = rate.value.toString()
        val flag = rate.flag
        Picasso.get().load(rate.flag).placeholder(R.drawable.default_flag).fit().into(holder.rateFlag)
    }

    override fun getItemCount(): Int {
        return rates.size
    }
}
