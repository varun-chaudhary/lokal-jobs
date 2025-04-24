package com.example.lokaljobs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobs.model.Job

class JobAdapter(
    private val jobList: List<Job>,
    private val onJobClick: (Job) -> Unit
) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    inner class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewTitle)
        val location: TextView = itemView.findViewById(R.id.textViewLocation)
        val salary: TextView = itemView.findViewById(R.id.textViewSalary)
        val phone: TextView = itemView.findViewById(R.id.textViewPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        Log.d("hehe", job.toString())

        holder.title.text = job.title

        val primaryDetails = job.primaryDetails
        Log.d("JobAdapter", "Job at position $position: ${job.primaryDetails}")
        primaryDetails?.place?.let {
            holder.location.visibility = View.VISIBLE
            holder.location.text = "Location: $it"
        } ?: run {
            holder.location.visibility = View.GONE
        }

        primaryDetails?.salary?.let {
            holder.salary.visibility = View.VISIBLE
            holder.salary.text = "Salary: $it"
        } ?: run {
            holder.salary.visibility = View.GONE
        }

        job.whatsappNumber?.let {
            holder.phone.visibility = View.VISIBLE
            holder.phone.text = "Phone: $it"
        } ?: run {
            holder.phone.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            if (job.primaryDetails != null) {
                onJobClick(job)
            } else {
                Log.w("JobAdapter", "Job primaryDetails is null. Cannot navigate.")
            }
        }
    }


    override fun getItemCount(): Int = jobList.size
}
