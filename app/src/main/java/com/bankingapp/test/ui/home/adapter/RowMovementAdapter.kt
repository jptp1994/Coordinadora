package com.bankingapp.test.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bankingapp.test.R
import com.bankingapp.test.databinding.RowMovementBinding
import com.bankingapp.test.ui.movements.model.Movement

class RowMovementAdapter(
    private val onClickListener: (Movement) -> Unit
) :
    ListAdapter<Movement, RowMovementAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<RowMovementBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_movement,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        //set movement data
        holder.binding.movement = data

        //event click to redirect to movement fragment
        holder.binding.clContainer.setOnClickListener {
            onClickListener(data)
        }
    }



    inner class ViewHolder(val binding: RowMovementBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<Movement>() {
        override fun areItemsTheSame(oldItem: Movement, newItem: Movement): Boolean {
            return oldItem.idMovement == newItem.idMovement
        }

        override fun areContentsTheSame(oldItem: Movement, newItem: Movement): Boolean {
            return oldItem.idMovement == newItem.idMovement
        }
    }
}
