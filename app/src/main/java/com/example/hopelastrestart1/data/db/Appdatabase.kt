package com.example.hopelastrestart1.data.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hopelastrestart1.data.db.entities.*
@Database(
    entities = [User::class, Organization::class, Quote::class, Project::class, UserAdded::class, Store::class, Plan ::class, Task ::class, AssignedToActivity::class, ReportedTask::class, CcAct::class, PipeAct::class, MhAct:: class, HscAct::class, HkAct::class, DPR::class, Activit::class, AssignedByActivity:: class, AssignedActivityNow:: class, AssignedToMeEntity::class, DprInfo::class, Invoice::class, Indent::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getOrganizationDao(): User_Organizations_Dao
    abstract fun getQuoteDao(): QuoteDao
    abstract fun getProjectDao():User_Organization_Project_Dao
    abstract fun getUserAddedDao():Organization_User_Dao
    abstract fun getStoreDao():Organization_Store_Dao
    abstract fun getProjectPlanDao():project_plans_Dao
    abstract fun getPlanTasksDao():plan_tasks_Dao
    abstract fun getUserReceivedTasksDao(): user_received_task_dao
    abstract fun getUserReportedTasksDao(): user_reported_task_dao
    abstract fun getTaskCCActivitiesDao(): task_cc_activity_dao
    abstract fun getTaskPipeActivitiesDao(): task_pipe_activity_dao
    abstract fun getTaskMHActivitiesDao(): task_mh_activity_dao
    abstract fun getTaskHSCActivitiesDao(): task_hsc_activity_dao
    abstract fun getTaskHKActivitiesDao(): task_hk_activity_dao
    abstract fun getUserSubmittedDprDao(): user_submitted_dpr_dao
    abstract fun getTaskActivitiesDao():task_activity_dao
    abstract fun getUserAssignedTaskActivitiesDao():user_assigned_task_activities_dao
    abstract fun getAssignedtomeDao():assigned_to_me_dao
    abstract fun getmydprs():My_Dpr_Dao
    abstract fun getInvoicesDao():Invoice_Dao
    abstract fun getIndentsDao():Indent_Dao



    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}