package com.example.lokaljobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lokaljobs.databinding.FragmentJobDeatilsBinding
import com.example.lokaljobs.model.Job

class JobDetailFragment : Fragment() {

    private var _binding: FragmentJobDeatilsBinding? = null
    private val binding get() = _binding!!

    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDeatilsBinding.inflate(inflater, container, false)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}