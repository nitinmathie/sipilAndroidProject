package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityAssignTaskBinding
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModelFactory
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.android.synthetic.main.activity_assign_task.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AssignTaskActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var viewModel: UserViewModel
    private lateinit var userSpinner: Spinner
    private lateinit var binding: ActivityAssignTaskBinding
    public lateinit var t : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_task)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_assign_task)
        userSpinner = findViewById<Spinner>(R.id.spinner_user)
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
        val username=intent.getStringExtra("username")
        val organization_name=intent.getStringExtra("organization_name")
        val activity_id=intent.getStringExtra("activity_id")
        val activity_name=intent.getStringExtra("activity_name")
        val activity_type=intent.getStringExtra("activity_type")
        val from_node=intent.getStringExtra("from_node")
        val to_node=intent.getStringExtra("to_node")
        val task_id=intent.getStringExtra("task_id")
        val project_name=intent.getStringExtra("project_name")
        val plan_id=intent.getStringExtra("plan_id")
        var letters : MutableList<Char> = mutableListOf<Char>()
        val myAdapter = ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item)

        // make an array of users with role type = Site Engineer
        val users1 by lazyDeferred {
            viewModel.users_byrole(username, organization_name)
        }
        Coroutines.main {
         //   progress_bar.show()
            val orgs = users1.await()
            orgs.observe(this, Observer {
                //    progress_bar.hide()
                it.size.toString()
                var nums = arrayOf<String>()

                for (i in 0..it.size - 1) {
                    nums = append(nums, it[i].username)
                }
                myAdapter.add(nums.toList())
            })
        }
        binding.activityType.setText(activity_type)
        binding.editTextFromNode.setText(from_node)
        binding.editTextToNode.setText(to_node)
        spinner_user.adapter=myAdapter
        spinner_user?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent!!.getItemAtPosition(position).toString()
                binding.err.setText(item)
            }
        }
        //on button click
        val add = findViewById<Button>(R.id.btn_submit_activity_machinery)
        add.setOnClickListener {

            val str: String = spinner_user.getSelectedItem().toString()

            val intent = Intent(this, AssignTaskMachinery::class.java)
            intent.putExtra("activity_id", activity_id)
            intent.putExtra("activity_name", activity_name)
            intent.putExtra("activity_type", activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("from_node", from_node)
            intent.putExtra("to_node", to_node)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("assigned_to", str.substring(1, str.length - 1))
            startActivity(intent)
        }


    }
    fun append(users: Array<String>, username: String?): Array<String> {
        val list: MutableList<String> = users.toMutableList()
        if (username != null) {
            list.add(username)
        }
        return list.toTypedArray()

    }
}



//
