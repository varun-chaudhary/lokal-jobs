package com.example.lokaljobs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokaljobs.databinding.FragmentJobsBinding
import com.example.lokaljobs.model.Job
import com.example.lokaljobs.network.RetrofitInstance
import kotlinx.coroutines.launch

class JobListFragment : Fragment() {

    private var _binding: FragmentJobsBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobAdapter: JobAdapter
    private val jobList = mutableListOf<Job>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchJobs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        jobAdapter = JobAdapter(jobList) { job ->
            val action = JobListFragmentDirections
                .actionJobListFragmentToJobDetailFragment(job)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = jobAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchJobs() {
        showLoading()
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getJobs()
                if (response.isSuccessful && response.body() != null) {
                    val jobsFromApi = response.body()?.results ?: emptyList()
                    val filteredJobs = jobsFromApi.filter {
                        it.id != null && it.title != null && it.primaryDetails != null
                    }

                    jobList.clear()
                    jobList.addAll(filteredJobs)
                    jobAdapter.notifyDataSetChanged()

                    if (jobList.isEmpty()) {
                        showEmpty()
                    } else {
                        showData()
                    }
                } else {
                    showError()
                    Toast.makeText(requireContext(), "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                showError()
                Log.e("API_ERROR", "Failed to fetch jobs: ${e.message}")
                Toast.makeText(requireContext(), "Failed to load jobs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }

    private fun showError() {
        binding.loadingView.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
    }

    private fun showEmpty() {
        binding.loadingView.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.errorView.visibility = View.GONE
        binding.emptyView.visibility = View.VISIBLE
    }

    private fun showData() {
        binding.loadingView.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
        binding.emptyView.visibility = View.GONE
    }
}
