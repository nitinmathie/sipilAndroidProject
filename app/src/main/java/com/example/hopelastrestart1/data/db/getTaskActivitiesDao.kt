package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.*
@Dao
interface task_activity_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveActs(acts: List<Activit>)
    @Query("SELECT * FROM Activit")
    fun getActs(): LiveData<List<Activit>>
    @Query("DELETE FROM Activit")
    fun deleteAll()
}
@Dao
interface task_cc_activity_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCCActivity(tasks: CcAct)
    @Query("SELECT * FROM CcAct")
    fun getCCActivity(): LiveData<CcAct>
    @Query("DELETE FROM CcAct")
    fun deleteAll()
}
@Dao
interface task_pipe_activity_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePipeActivity(tasks: PipeAct)
    @Query("SELECT * FROM pipeAct")
    fun getPipeActivity(): LiveData<PipeAct>
    @Query("DELETE FROM PipeAct")
    fun deleteAll()
}
@Dao
interface task_mh_activity_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMHActivity(tasks: MhAct)
    @Query("SELECT * FROM MhAct")
    fun getmhActivity(): LiveData<MhAct>
    @Query("DELETE FROM mhAct")
    fun deleteAll()
}
@Dao
interface task_hsc_activity_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHSCActivity(tasks: HscAct)
    @Query("SELECT * FROM HscAct")
    fun getHSCActivity(): LiveData<HscAct>
    @Query("DELETE FROM HscAct")
    fun deleteAll()

}
@Dao
interface task_hk_activity_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHKActivity(tasks: HkAct)
    @Query("SELECT * FROM HkAct")
    fun getHKActivity(): LiveData<HkAct>
    @Query("DELETE FROM HkAct")
    fun deleteAll()
}
@Dao
interface user_submitted_dpr_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAssignedActivity(dpr: DPR)
    @Query("SELECT * FROM DPR")
    fun getalldprs(): LiveData<List<DPR>>
    @Query("DELETE FROM DPR")
    fun deleteAll()
}