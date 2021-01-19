package com.example.android.houserental.ui.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.houserental.R
import com.example.android.houserental.domain.model.House
import com.squareup.picasso.Picasso

class ItemHouseAdapter (private val context: Context, private val clickListener:OnItemClick)
    : RecyclerView.Adapter<ItemHouseAdapter.ItemViewHolder>() {

        var houseList: List<House> = emptyList()

        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val price: TextView = view.findViewById(R.id.tv_price)
            val houseImage:ImageView = view.findViewById(R.id.iv_house_image)
            val zip: TextView = view.findViewById(R.id.tv_zip)
            val bedImage:ImageView = view.findViewById(R.id.iv_bed)
            val bedNumber: TextView = view.findViewById(R.id.tv_bed_number)
            val bathroom:ImageView = view.findViewById(R.id.iv_bathroom)
            val bathroomNumber: TextView = view.findViewById(R.id.tv_bathroom_number)
            val sizeImage:ImageView = view.findViewById(R.id.iv_size)
            val sizeNumber: TextView = view.findViewById(R.id.tv_size)
            val distanceImage:ImageView = view.findViewById(R.id.iv_distance)
            val distanceNumber: TextView = view.findViewById(R.id.tv_distance)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            // create a new view
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)

            return ItemViewHolder(adapterLayout)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

            val item = houseList[position]
            holder.price.text = "$${item.price}"
            Picasso.get()
                .load("https://intern.docker-dev.d-tt.nl/${item.image}")
                .fit()
                .centerCrop()
                .into(holder.houseImage)
            holder.zip.text = item.zip
            holder.bedImage.setImageResource(R.drawable.ic_bed)
            holder.bedNumber.text=item.bedrooms.toString()
            holder.bathroom.setImageResource(R.drawable.ic_bath)
            holder.bathroomNumber.text=item.bathrooms.toString()
            holder.sizeImage.setImageResource(R.drawable.ic_layers)
            holder.sizeNumber.text= item.size.toString()
            holder.distanceImage.setImageResource(R.drawable.ic_location)
            holder.distanceNumber.text= item.distance


            //click action
            holder.itemView.setOnClickListener {
                clickListener.onItemClickListener(position)
            }
        }

        override fun getItemCount()= houseList.size

        interface OnItemClick {
            fun onItemClickListener (position: Int)
        }
}