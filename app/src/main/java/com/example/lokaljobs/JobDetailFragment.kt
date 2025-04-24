package com.example.lokaljobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.lokaljobs.dao.JobDao
import com.example.lokaljobs.database.AppDatabase
import com.example.lokaljobs.databinding.FragmentJobDeatilsBinding
import com.example.lokaljobs.model.Job
import kotlinx.coroutines.launch

class JobDetailFragment : Fragment() {

    private var _binding: FragmentJobDeatilsBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobDao: JobDao

    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDeatilsBinding.inflate(inflater, container, false)
        jobDao = AppDatabase.getDatabase(requireContext()).jobDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        job = arguments?.getParcelable("job") ?: return

        binding.tvTitle.text = job.title

        job.companyName?.let {
            binding.tvCompanyName.text = "Company: $it"
            binding.tvCompanyName.visibility = View.VISIBLE
        }

        job.whatsappNumber?.let {
            binding.tvWhatsapp.text = "WhatsApp: $it"
            binding.tvWhatsapp.visibility = View.VISIBLE
        }

        job.primaryDetails?.salary?.let {
            binding.tvSalary.text = "Salary: $it"
            binding.tvSalary.visibility = View.VISIBLE
        }

        job.primaryDetails?.place?.let {
            binding.tvPlace.text = "Place: $it"
            binding.tvPlace.visibility = View.VISIBLE
        }

        job.primaryDetails?.jobType?.let {
            binding.tvJobType.text = "Job Type: $it"
            binding.tvJobType.visibility = View.VISIBLE
        }

        job.primaryDetails?.experience?.let {
            binding.tvExperience.text = "Experience: $it"
            binding.tvExperience.visibility = View.VISIBLE
        }

        job.primaryDetails?.qualification?.let {
            binding.tvQualification.text = "Qualification: $it"
            binding.tvQualification.visibility = View.VISIBLE
        }

        binding.btnBookmark.setOnClickListener {
            lifecycleScope.launch {
                jobDao.insertJob(job)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}