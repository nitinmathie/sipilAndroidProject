package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityReportMaterialBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.util.lazyDeferred
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ReportMaterialActivity () : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityReportMaterialBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_material)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        val material_1 = binding.textViewMaterial1Name.text.toString().trim()
        val material_2 = binding.textViewMaterial2Name.text.toString().trim()
        val material_3 = binding.textViewMaterial3Name.text.toString().trim()
        val materail_1_quantity = binding.editTextMaterial1Quantity.text.toString().trim()
        val materail_2_quantity = binding.editTextMaterial2Quantity.text.toString().trim()
        val materail_3_quantity = binding.editTextMaterial3Quantity.text.toString().trim()
        binding.btnSubmitActivityMaterial.setOnClickListener {
            val tasks by lazyDeferred {
                viewModel.submitMaterialReport(username,organization_name, project_name, plan_id, task_id,
                    activity_name,
                    material_1,
                    material_2,
                    material_3,
                    materail_1_quantity,
                    materail_2_quantity,
                    materail_3_quantity

                )
            }


//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            val intent = Intent(this, SiteHomeActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }

    }
}