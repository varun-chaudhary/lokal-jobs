package com.example.lokaljobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobs.model.Job


class JobsFragment : Fragment() {

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

        jobAdapter = JobAdapter(jobList)
        recyclerView.adapter = jobAdapter

        loadJobs()
    }

    private fun loadJobs() {
        // Simulated data — replace with your API result later
        jobList.addAll(
            listOf(
                Job(1, "Android Developer", "Bangalore", "₹8 LPA", "9876543210"),
                Job(2, "Backend Engineer", "Mumbai", "₹12 LPA", "8765432109"),
                Job(1, "Android Developer", "Bangalore", "₹8 LPA", "9876543210"),
                Job(2, "Backend Engineer", "Mumbai", "₹12 LPA", "8765432109"),
                Job(1, "Android Developer", "Bangalore", "₹8 LPA", "9876543210"),
                Job(2, "Backend Engineer", "Mumbai", "₹12 LPA", "8765432109"),
                Job(1, "Android Developer", "Bangalore", "₹8 LPA", "9876543210"),
                Job(2, "Backend Engineer", "Mumbai", "₹12 LPA", "8765432109"),                Job(1, "Android Developer", "Bangalore", "₹8 LPA", "9876543210"),
                Job(2, "Backend Engineer", "Mumbai", "₹12 LPA", "8765432109"),
                Job(1, "Android Developer", "Bangalore", "₹8 LPA", "9876543210"),
                Job(2, "Backend Engineer", "Mumbai", "₹12 LPA", "8765432109"),
            )
        )
        jobAdapter.notifyDataSetChanged()
    }
}
