package com.example.lokaljobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobs.model.Job

class JobAdapter(
    private val jobList: List<Job>
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
        holder.title.text = job.title

        val primaryDetails = job.primaryDetails
        holder.location.text = "Location: ${primaryDetails?.place ?: "N/A"}"
        holder.salary.text = "Salary: ${primaryDetails?.salary ?: "N/A"}"
        holder.phone.text = "Phone: ${job.whatsappNumber ?: "N/A"}"
    }

    override fun getItemCount(): Int = jobList.size
}
