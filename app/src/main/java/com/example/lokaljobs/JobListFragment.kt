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
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobs.model.Job
import com.example.lokaljobs.network.RetrofitInstance
import kotlinx.coroutines.launch

class JobListFragment : Fragment() {

    private lateinit var jobAdapter: JobAdapter
    private lateinit var recyclerView: RecyclerView
    private val jobList = mutableListOf<Job>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jobs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        jobAdapter = JobAdapter(jobList) { job ->

            val action = JobListFragmentDirections
                .actionJobListFragmentToJobDetailFragment(job)
            findNavController().navigate(action)
        }
        recyclerView.adapter = jobAdapter

        fetchJobs()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchJobs() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getJobs()
                if (response.isSuccessful && response.body() != null) {
                    val jobsFromApi = response.body()?.results ?: emptyList()

                    Log.d("hehe", response.body().toString())
                    jobList.clear()
                    val filteredJobs = jobsFromApi.filter { job ->  // jobs without title or primary details will be ignored
                        job.title != null && job.primaryDetails != null
                    }
                    jobList.addAll(filteredJobs)
                    jobAdapter.notifyDataSetChanged()
                }
                else {
                    Toast.makeText(requireContext(), "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to fetch jobs: ${e.message}")
                Toast.makeText(requireContext(), "Failed to load jobs", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
