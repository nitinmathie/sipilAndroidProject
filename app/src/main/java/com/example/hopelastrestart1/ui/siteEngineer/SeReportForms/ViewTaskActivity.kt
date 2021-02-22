package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityViewTaskBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ViewTaskActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityViewTaskBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        binding.editTextFromNode.setText(activity_name)
        binding.editTextToNode.setText(activity_name)
        binding.activityType.setText(activity_name)

    }
}