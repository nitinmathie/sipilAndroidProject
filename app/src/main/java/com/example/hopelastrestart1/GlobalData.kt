package com.example.hopelastrestart1

import com.example.hopelastrestart1.data.db.entities.*
import com.example.hopelastrestart1.data.network.responses.GetAssignedMachinery
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.model.Project
import com.example.hopelastrestart1.ui.home.organization.OrganizationActivity
import org.json.JSONObject

class GlobalData {

    var token: String? = null
    var userEmail: String? = null
    var loginRole: String? = null
    var navgationType: String? = null
    var assignTaskWorkType: String? = null
    var orgName: String? = null
    var projectName: String? = null
    var planName: String? = null
    var storeName: String? = null
    var invoiceId: Int? = null
    var dispatchedId: String? = null
    var storeType: String? = null
    var taskName: String? = null
    var activityName: String? = null
    var assignedTo: String? = null
    var estimatedTimeLine: String? = null
    var projects: List<Project>? = null
    var activity: Activit? = null
    var org: Organization? = null
    var project: Project? = null
    var plan: Plan? = null
    var task: Task? = null
    var user: UserAdded? = null
    var getAssignedMachinery: GetAssignedMachinery? = null
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
    var fetchReport: FetchReportResponse? = null
    var report: Report? = null

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