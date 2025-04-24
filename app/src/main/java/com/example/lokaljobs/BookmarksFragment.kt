package com.example.lokaljobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokaljobs.dao.JobDao
import com.example.lokaljobs.database.AppDatabase
import com.example.lokaljobs.databinding.FragmentBookmarksBinding
import com.example.lokaljobs.model.Job
import kotlinx.coroutines.launch

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobDao: JobDao

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


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchBookmarkedJobs()
    }

    private fun fetchBookmarkedJobs() {
        lifecycleScope.launch {
            jobDao.getAllBookmarkedJobs().collect { jobList ->
                val adapter = JobAdapter(jobList) { job ->
                    val action = BookmarksFragmentDirections
                        .actionBookmarksFragmentToJobDetailFragment(job)
                    findNavController().navigate(action)
                }
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}