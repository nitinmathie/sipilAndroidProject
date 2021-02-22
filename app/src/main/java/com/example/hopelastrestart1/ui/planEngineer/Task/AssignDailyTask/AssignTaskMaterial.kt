package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.hopelastrestart1.R

class AssignTaskMaterial : AppCompatActivity() {
    lateinit var anySpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_task_material)
        var material_1 =findViewById<EditText>(R.id.text_view_material1_name).getText().toString()
        var material_2 =findViewById<EditText>(R.id.text_view_material2_name).getText().toString()
        var material_3 =findViewById<EditText>(R.id.text_view_material3_name).getText().toString()
        var material_1_quantity =findViewById<EditText>(R.id.edit_text_material1_quantity).getText().toString()
        var material_2_quantity =findViewById<EditText>(R.id.edit_text_material2_quantity).getText().toString()
        var material_3_quantity =findViewById<EditText>(R.id.edit_text_material3_quantity).getText().toString()
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
        val jcb_quantity=intent.getStringExtra("jcb_quantity")
        val hydra_quantity=intent.getStringExtra("hydra_quantity")
        val tractor_quantity=intent.getStringExtra("tractor_quantity")
        val watertanker_quantity=intent.getStringExtra("watertanker_quantity")
        val tractorcompressor_quantity=intent.getStringExtra("tractorcompressor_quantity")
        val jcb_runtime=intent.getStringExtra("jcb_runtime")
        val hydra_runtime=intent.getStringExtra("hydra_runtime")
        val tractor_runtime=intent.getStringExtra("tractor_runtime")
        val watertanker_runtime=intent.getStringExtra("watertanker_runtime")
        val tractorcompressor_runtime=intent.getStringExtra("tractorcompressor_runtime")


        //passing to intent
        var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_material)
        btn_to_material.setOnClickListener {
            val intent = Intent(this, AssignTaskManpower::class.java)
            intent.putExtra("material_1_quantity",material_1_quantity)
            intent.putExtra("material_2_quantity", material_2_quantity)
            intent.putExtra("material_3_quantity", material_3_quantity)

            intent.putExtra("material_1",material_1)
            intent.putExtra("material_2", material_2)
            intent.putExtra("material_3", material_3)
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


            startActivity(intent)
        }

    }
}