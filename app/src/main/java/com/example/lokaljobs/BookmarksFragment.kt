package com.example.lokaljobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokaljobs.dao.JobDao
import com.example.lokaljobs.database.AppDatabase
import com.example.lokaljobs.databinding.FragmentBookmarksBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobDao: JobDao
    private lateinit var jobAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        jobDao = AppDatabase.getDatabase(requireContext()).jobDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchBookmarkedJobs()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        jobAdapter = JobAdapter(emptyList()) { job ->
            val action = BookmarksFragmentDirections
                .actionBookmarksFragmentToJobDetailFragment(job)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = jobAdapter
    }

    private fun fetchBookmarkedJobs() {
        showLoading()
        lifecycleScope.launch {
            jobDao.getAllBookmarkedJobs()
                .catch {
                    showError()
                }
                .collect { jobList ->
                    if (jobList.isEmpty()) {
                        showEmpty()
                    } else {
                        showData()
                        jobAdapter = JobAdapter(jobList) { job ->
                            val action = BookmarksFragmentDirections
                                .actionBookmarksFragmentToJobDetailFragment(job)
                            findNavController().navigate(action)
                        }
                        binding.recyclerView.adapter = jobAdapter
                    }
                }
        }
    }

    private fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }

    private fun showData() {
        binding.loadingView.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.emptyView.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }

    private fun showEmpty() {
        binding.loadingView.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
    }

    private fun showError() {
        binding.loadingView.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
