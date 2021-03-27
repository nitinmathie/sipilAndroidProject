package com.example.hopelastrestart1.ui.home.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityMyProjectsBinding
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.viewmodel.MainViewModel

class MyProjectsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyProjectsBinding
    private lateinit var viewModel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_projects)


    }
}