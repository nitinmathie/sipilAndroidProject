package com.example.hopelastrestart1

import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.data.db.entities.Task
import com.example.hopelastrestart1.data.db.entities.User
import com.example.hopelastrestart1.data.db.entities.UserAdded
import com.example.hopelastrestart1.model.*
import org.json.JSONObject

class GlobalData {

    var token: String? = null
    var userEmail: String? = null
    var assignedTo: String? = null
    var estimatedTimeLine: String? = null
    var projects: List<Project>? = null
    var activity: Activit? = null
    var task: Task? = null
    var user: UserAdded? = null
    var updateTaskActivity: UpdateTaskActivity? = null
    var updatePipelineActivity: UpdatePipelineActivity? = null
    var updateMHActivity: UpdateMHActivity? = null
    var updateHscActivity: UpdateHscActivity? = null
    var updateHouseKeepingActivity: UpdateHouseKeepingActivity? = null
    var updateRoadRestorationActivity: UpdateRoadRestorationActivity? = null
    var materialList: List<MachinesQuantity>? = null
    var machineryList: List<MachinesQuantity>? = null
    var jsonObject: JSONObject? = null
    var getAssignedTaskActivitesModel: GetAssignedTaskActivitesModel? = null

    private constructor() {

    }


    companion object {
        private var instance: GlobalData? = null
        val getInstance: GlobalData
            get() {
                if (instance == null) {
                    instance = GlobalData()
                }

                return instance!!
            }
    }

    fun reset() {
        GlobalData.instance = GlobalData()
    }

}