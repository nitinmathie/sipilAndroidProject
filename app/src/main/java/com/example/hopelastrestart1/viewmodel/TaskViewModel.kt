package com.example.hopelastrestart1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.util.HashMap

class TaskViewModel(private val apiService: ApiService) : ViewModel() {

    fun addTask(addTask: AddTask) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addTask(addTask)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getTasks(getTasks: GetTasks) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getTasks(getTasks)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getTaskActivites(getTaskActivities: GetTaskActivities) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getTaskActivites(getTaskActivities)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun updateTaskActivity(updateTaskActivity: UpdateTaskActivity) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.updateTaskActivity(updateTaskActivity)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun updateTaskPipelineActivity(updateTaskActivity: UpdatePipelineActivity) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.updateTaskPipeActivity(updateTaskActivity)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun updateHKActvity(updateTaskActivity: UpdateHouseKeepingActivity) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.updateHKActvity(updateTaskActivity)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun updateHSCActvity(updateTaskActivity: UpdateHscActivity) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.updateHSCActvity(updateTaskActivity)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun updateMHActivity(updateTaskActivity: UpdateMHActivity) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.updateMHActivity(updateTaskActivity)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun updateRoadRestoreActivity(updateTaskActivity: UpdateRoadRestorationActivity) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.updateRoadRestoreActivity(updateTaskActivity)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }


    fun assignTaskActivity(assignTaskActivityModel: AssignTaskActivityModel) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.assginTaskActivity(assignTaskActivityModel)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun getAssignedTasks(getTasksAssignedToMe: GetTasksAssignedToMe) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getAssignedTasks(getTasksAssignedToMe)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getAssignedTasksToMe(getTasksAssignedToMe: GetTasksAssignedToMe) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getAssignedTasksToMe(getTasksAssignedToMe)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun getMachines(getMachinesAndMaterialModel: GetMachinesAndMaterialModel) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getMachines(getMachinesAndMaterialModel)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getMaterials(getMachinesAndMaterialModel: GetMachinesAndMaterialModel) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getMaterials(getMachinesAndMaterialModel)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getRoleBasedUsers(getRoledBasedUsers: GetRoledBasedUsers) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getRoleBasedUsers(getRoledBasedUsers)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }

    }


    fun submitWorkReport(submitCCwork: SubmitTaskReport) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.submitWorkReport(submitCCwork)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun submitMachineReport(submitMachineReport: SubmitMachineryReport) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.submitMachinereport(submitMachineReport)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


    fun submitMaterialReport(submitMaterialReport: SubmitMaterialReport) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.submitMaterialReport(submitMaterialReport)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun submitManPowerReport(submitManPowerReport: SubmitManPowerReport) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.submitManpowerReport(submitManPowerReport)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getUserReports(getReports: GetReports) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getUserReports(getReports)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getReceivedReports(getReports: GetReports) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getReceivedReports(getReports)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


}


/*  fun createUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.createUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }

  fun updateUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.updateUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }
  fun deleteUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.deleteUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }*/
