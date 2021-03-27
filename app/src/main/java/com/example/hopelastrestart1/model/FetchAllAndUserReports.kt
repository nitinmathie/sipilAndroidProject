package com.example.hopelastrestart1.model

import com.example.hopelastrestart1.data.network.responses.MachineryReports
import com.example.hopelastrestart1.data.network.responses.ManPowerReports
import com.example.hopelastrestart1.data.network.responses.MaterialReports
import com.example.hopelastrestart1.data.network.responses.WorkReports

class FetchAllAndUserReports(
    var material_reports: List<MaterialReports>?,
    var reports_work: List<WorkReports>?,
    var reports_manpower: List<ManPowerReports>?,
    var reports_machinery: List<MachineryReports>?,
    val isSuccessful: String?,
    val status_code: Int?
)