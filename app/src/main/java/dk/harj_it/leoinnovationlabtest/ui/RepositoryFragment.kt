package dk.harj_it.leoinnovationlabtest.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dk.harj_it.leoinnovationlabtest.MainViewModel
import dk.harj_it.leoinnovationlabtest.R
import dk.harj_it.leoinnovationlabtest.databinding.FragmentRepositoriesBinding
import dk.harj_it.leoinnovationlabtest.databinding.ItemRepositoryBinding
import dk.harj_it.leoinnovationlabtest.models.Repository

class RepositoryFragment : Fragment() {
    private lateinit var binding: FragmentRepositoriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repositories, container, false)

        binding.apply {
            lifecycleOwner = this@RepositoryFragment
            vm = viewModel

            val adapter = RepositoryAdapter()
            recyclerView.adapter = adapter

            viewModel.repositories.observe(requireActivity(), Observer {
                it?.let { repositories ->
                    adapter.updateItems(repositories)
                }
            })

            return root
        }
    }

    inner class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
        private var list: MutableList<Repository> = mutableListOf()

        fun updateItems(l: List<Repository>) {
            list.clear()
            list.addAll(l)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository,parent,false))

        override fun getItemCount(): Int = list.size


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindToItems(position)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val itemBinding: ItemRepositoryBinding? = DataBindingUtil.bind(itemView)

            init {
                itemBinding?.apply {
                    root.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(repository().htmlUrl))) }
                    cloneUrlTextview?.setOnClickListener { copyToClipBoard(repository().cloneUrl) }
                }
            }

            private fun repository()=  list[adapterPosition]

            private fun copyToClipBoard(gitUrl: String?) {
                getSystemService<ClipboardManager>(requireContext(), ClipboardManager::class.java)
                    ?.setPrimaryClip(ClipData.newPlainText("label", gitUrl))

                Snackbar.make(binding.root, getString(R.string.repository_copied_git_url), Snackbar.LENGTH_LONG).show()
            }

            fun bindToItems(position: Int) {
                itemBinding?.model = list[position]
            }
        }
    }
}