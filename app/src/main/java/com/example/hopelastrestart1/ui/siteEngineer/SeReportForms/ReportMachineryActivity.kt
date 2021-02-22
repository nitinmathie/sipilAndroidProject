package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityReportMachineryBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.util.lazyDeferred
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ReportMachineryActivity () : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityReportMachineryBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_machinery)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        val hydra_quantity = binding.editTextHydraQuantity.text.toString().trim()
        val hydra_hours = binding.editTextHydraRuntime.text.toString().trim()
        val jcb_quantity = binding.editTextJcbQuantity.text.toString().trim()
        val jcb_hours = binding.editTextJcbRuntime.text.toString().trim()
        val tractor_quantity = binding.editTextTractorQuantity.text.toString().trim()
        val tractor_hours = binding.editTextTractorRuntime.text.toString().trim()
        val tractor_compressor_quantity = binding.editTextTractorcompressorQuantity.text.toString().trim()
        val tractor_compressor_hours = binding.editTextTractorcompressorRuntime.text.toString().trim()
        val water_tanker_quantity = binding.editTextWatertankerQuantity.text.toString().trim()
        val water_tanker = binding.editTextWatertankerRuntime.text.toString().trim()
        binding.btnSubmitActivityMaterial.setOnClickListener {
            val tasks by lazyDeferred {
                viewModel.submitMachineryReport(username,organization_name, project_name, plan_id, task_id,
                    activity_name,
                    hydra_quantity,
                    hydra_hours,
                    jcb_quantity,
                    jcb_hours,
                    tractor_quantity,
                    tractor_hours,
                    tractor_compressor_quantity,
                    tractor_compressor_hours,
                    water_tanker_quantity,
                    water_tanker

                )
            }

//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            val intent = Intent(this, SiteHomeActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }

    }
}