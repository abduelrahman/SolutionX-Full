package am.leon.solutionx.presentation.adapters.singleSelection

import am.leon.solutionx.databinding.ItemSingleCheckBinding
import am.leon.solutionx.databinding.ItemSingleRadioBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
class SingleSelectionAdapter(
    private val viewType: SingleSelectionViewType,
    private val callback: SingleSelectionCallback? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var checkedPosition = -1
    private var items: MutableList<SingleSelection> = ArrayList()

    override fun getItemViewType(position: Int): Int = when (viewType) {
        SingleSelectionViewType.SELECTION_RADIO -> SingleSelectionViewType.SELECTION_RADIO.index
        SingleSelectionViewType.SELECTION_CHECK -> SingleSelectionViewType.SELECTION_CHECK.index
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SingleSelectionViewType.SELECTION_RADIO.index -> ViewHolderRadio(
                ItemSingleRadioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            SingleSelectionViewType.SELECTION_CHECK.index -> ViewHolderCheck(
                ItemSingleCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            else -> throw Exception("View type not supported...")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            SingleSelectionViewType.SELECTION_RADIO.index -> with(holder as ViewHolderRadio) {
                this.bind(items[position])
            }

            else -> with(holder as ViewHolderCheck) {
                this.bind(items[position])
            }
        }
    }

    fun setSelectedItem(item: SingleSelection? = null) {
        if (item == null) {
            checkedPosition = 0
            notifyDataSetChanged()
            return
        }

        if (items.contains(item)) {
            val position = items.indexOf(item)
            if (checkedPosition != position) {
                checkedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    fun getSelectedItem(): SingleSelection = items[when (checkedPosition) {
        -1 -> 0
        else -> checkedPosition
    }
    ]

    private fun applySelector(item: SingleSelection) {
        if (items.contains(item)) {
            val position = items.indexOf(item)
            if (checkedPosition != position) {
                checkedPosition = position
                notifyDataSetChanged()
                callback?.onSingleItemSelected(item)
            }
        }
    }

    fun setItems(items: List<SingleSelection>) {
        this.items = items as MutableList<SingleSelection>
        notifyDataSetChanged()
    }

    fun addAll(items: List<SingleSelection>) = items.forEach {
        this.items.add(it)
        notifyItemInserted(items.lastIndex)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolderRadio(private val binding: ItemSingleRadioBinding) : View.OnClickListener,
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener(this)
            binding.imgItem.setOnClickListener(this)
        }

        fun bind(item: SingleSelection): Unit = with(binding) {
            val isItemSelected = checkedPosition == bindingAdapterPosition
            txtItemName.text = item.name
            root.isSelected = isItemSelected

            imgItem.visibility = if (item.getIconRes() != -1) {
                imgItem.setImageResource(item.getIconRes())
                View.VISIBLE
            } else
                View.GONE
        }

        override fun onClick(view: View) {
            applySelector(items[bindingAdapterPosition])
        }
    }

    inner class ViewHolderCheck(private val binding: ItemSingleCheckBinding) : View.OnClickListener,
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: SingleSelection): Unit = with(binding) {
            val isItemSelected = checkedPosition == bindingAdapterPosition
            txtItemName.text = item.name
            root.isSelected = isItemSelected

            imgCheck.visibility = if (isItemSelected)
                View.VISIBLE
            else
                View.GONE
        }

        override fun onClick(view: View) {
            applySelector(items[bindingAdapterPosition])
        }
    }
}