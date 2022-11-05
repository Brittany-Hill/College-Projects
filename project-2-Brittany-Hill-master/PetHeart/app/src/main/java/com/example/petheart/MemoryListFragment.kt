package com.example.petheart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "MemoryListFragment"

class MemoryListFragment : Fragment() {
    private lateinit var memoryRecyclerView: RecyclerView
    private var adapter: MemoryAdapter? = MemoryAdapter(emptyList())
    private val memoryListViewModel: MemoryListViewModel by lazy {
        ViewModelProviders.of(this).get(MemoryListViewModel::class.java)
    }
    private var callbacks
            : Callbacks? = null

    interface Callbacks {
        fun onMemorySelected(memoryId: UUID)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as? Callbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_memory_list, container, false)

        memoryRecyclerView =
            view.findViewById(R.id.memory_recycler_view) as RecyclerView
        memoryRecyclerView.layoutManager = LinearLayoutManager(context)
        memoryRecyclerView.adapter = adapter
        return view
    }

    override fun onStart() {
        super.onStart()
        memoryListViewModel.memoryListLiveData.observe(
            viewLifecycleOwner,
            Observer { memorys ->
                memorys?.let {
                    Log.i(TAG, "Got memoryLiveData ${memorys.size}")
                    updateUI(memorys)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_memory_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_memory -> {
                val memory = Memory()
                memoryListViewModel.addMemory(memory)
                callbacks?.onMemorySelected(memory.id)
                true
            }
            R.id.filter_select -> {
                memoryListViewModel.memoryListLiveData.observe(
                    viewLifecycleOwner,
                    Observer { memorys ->
                        memorys?.let {
                            Log.i(TAG, "Got memoryLiveData ${memorys.size}")
                            updateUI(memorys)
                        }
                    }
                )
                true
            }

            R.id.favorite_filter -> {
                memoryListViewModel.memoryFavoriteLiveData.observe(
                    viewLifecycleOwner,
                    Observer { memorys ->
                        memorys?.let {
                            Log.i(TAG, "Got memoryLiveData ${memorys.size}")
                            updateUI(memorys as List<Memory>)
                        }
                    }
                )
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun updateUI(memorys: List<Memory>) {
        adapter?.let {
            it.memorys = memorys
        } ?: run {
            adapter = MemoryAdapter(memorys)
        }
        memoryRecyclerView.adapter = adapter
    }

    private inner class MemoryHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var memory: Memory

        private val titleTextView: TextView = itemView.findViewById(R.id.memory_name)
        private val dateTextView: TextView = itemView.findViewById(R.id.memory_date)
        private val photoView: ImageView = itemView.findViewById(R.id.memory_photo)
        private val favoriteView: ImageView = itemView.findViewById(R.id.favorite)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(memory: Memory) {
            this.memory = memory
            titleTextView.text = this.memory.name
            dateTextView.text = this.memory.date.toString()

            val photoFile = memoryListViewModel.getPhotoFile(memory)
            if (photoFile.exists()) {
                val bitmap = getScaledBitmap(
                    photoFile.toString(),
                    requireActivity()
                )
                photoView.setImageBitmap(bitmap)
            }
            favoriteView.visibility = if (memory.favorite) {
                View.VISIBLE
            } else {
                View.GONE
            }

        }

        override fun onClick(v: View) {
            callbacks?.onMemorySelected(memory.id)
        }
    }

    private inner class MemoryAdapter(var memorys: List<Memory>) :
        RecyclerView.Adapter<MemoryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                MemoryHolder {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.list_item_memorys, parent, false)
            return MemoryHolder(view)
        }

        override fun onBindViewHolder(holder: MemoryHolder, position: Int) {
            val memory = memorys[position]
            holder.bind(memory)
        }

        override fun getItemCount() = memorys.size
    }

    companion object {
        fun newInstance(): MemoryListFragment {
            return MemoryListFragment()
        }
    }
}
