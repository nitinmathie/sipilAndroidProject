package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.hopelastrestart1.R

class AssignTaskMachinery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_task_machinery)
        var jcb_quantity =findViewById<EditText>(R.id.edit_text_jcb_quantity).getText().toString()
        var hydra_quantity =findViewById<EditText>(R.id.edit_text_hydra_quantity).getText().toString()
        var tractor_quantity =findViewById<EditText>(R.id.edit_text_tractor_quantity).getText().toString()
        var watertanker_quantity =findViewById<EditText>(R.id.edit_text_watertanker_quantity).getText().toString()
        var tractorcompressor_quantity =findViewById<EditText>(R.id.edit_text_tractorcompressor_quantity).getText().toString()
        //runtime
        var jcb_runtime =findViewById<EditText>(R.id.edit_text_jcb_runtime).getText().toString()
        var hydra_runtime =findViewById<EditText>(R.id.edit_text_hydra_runtime).getText().toString()
        var tractor_runtime=findViewById<EditText>(R.id.edit_text_tractor_runtime).getText().toString()
        var watertanker_runtime =findViewById<EditText>(R.id.edit_text_watertanker_runtime).getText().toString()
        var tractorcompressor_runtime =findViewById<EditText>(R.id.edit_text_tractorcompressor_runtime).getText().toString()
       val username=intent.getStringExtra("username")
        val organization_name=intent.getStringExtra("organization_name")
        val activity_id=intent.getStringExtra("activity_id")
        val activity_name=intent.getStringExtra("activity_name")
        val activity_type=intent.getStringExtra("activity_type")
        val from_node=intent.getStringExtra("from_node")
        val to_node=intent.getStringExtra("to_node")
        val task_id=intent.getStringExtra("task_id")
        val project_name=intent.getStringExtra("project_name")
        val assigned_to=intent.getStringExtra("assigned_to")


        val material_1_quantity=intent.getStringExtra("material_1_quantity")
        val material_2_quantity=intent.getStringExtra("material_2_quantity")
        val material_3_quantity=intent.getStringExtra("material_3_quantity")
        val material_1=intent.getStringExtra("material_1")
        val material_2=intent.getStringExtra("material_2")
        val material_3=intent.getStringExtra("material_3")
        //val assigned_to=intent.getStringExtra("assigned_to")
        val task=intent.getStringExtra("task")
        val activity=intent.getStringExtra("activity")


        var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_material)
        btn_to_material.setOnClickListener {
            val intent = Intent(this, AssignTaskMaterial::class.java)
            intent.putExtra("jcb_quantity",jcb_quantity)
            intent.putExtra("hydra_quantity", hydra_quantity)
            intent.putExtra("tractor_quantity", tractor_quantity)
            intent.putExtra("watertanker_quantity",watertanker_quantity)
            intent.putExtra("tractorcompressor_quantity", tractorcompressor_quantity)
            intent.putExtra("jcb_runtime", jcb_runtime)
            intent.putExtra("hydra_runtime",hydra_runtime)
            intent.putExtra("tractor_runtime", tractor_runtime)
            intent.putExtra("watertanker_runtime", watertanker_runtime)
            intent.putExtra("tractorcompressor_runtime",tractorcompressor_runtime)
            intent.putExtra("activity_id", activity_id)
            intent.putExtra("activity_name", activity_name)
            intent.putExtra("activity_type", activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("from_node", from_node)
            intent.putExtra("to_node", to_node)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("assigned_to", assigned_to)
            intent.putExtra("material_1_quantity",material_1_quantity)
            intent.putExtra("material_2_quantity", material_2_quantity)
            intent.putExtra("material_3_quantity", material_3_quantity)

            intent.putExtra("material_1",material_1)
            intent.putExtra("material_2", material_2)
            intent.putExtra("material_3", material_3)
            intent.putExtra("assigned_to",assigned_to)
            intent.putExtra("task", task)
            intent.putExtra("activity", activity)
            startActivity(intent)
        }

    }
}