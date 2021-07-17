package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.Indent
import com.example.hopelastrestart1.data.db.entities.Invoice
import com.example.hopelastrestart1.data.db.entities.Store
import com.example.hopelastrestart1.model.InvoiceMachines
import com.example.hopelastrestart1.model.InvoiceMaterial
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject


data class StoreResponse(
    val isSuccessful: Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val Error: String?,
    val status_code: String?,
    val message: String?,
    val stores: List<Store>?
)

data class InvoiceResponse(
    val isSuccessful: Boolean?,
    val status_code: String?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val invoice: List<Invoice>?
)

data class AddMaterialInvoice(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var organization_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("store_name") var store_name: String,
    @SerializedName("materials") var materials: JsonArray,
    @SerializedName("invoice_number") var invoice_number: String,
    @SerializedName("vendor_name") var vendor_name: String,

    )

data class GetAllInvoices(
    val invoices: List<Invoices>?,
    val status_code: String?
)

data class Invoices(
    val invoice_id: Int?,
    val added_by: String?
)

data class GetSingleInvoice(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var organization_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("store_name") var store_name: String,
    @SerializedName("invoice_id") var invoice_id: Int?,
)

data class GetSingleInvoiceResponse(
    val invoice: SingleInvoice?,
    val status_code: String?
)

data class SingleInvoice(
    val invoice_id: Int?,
    val organization_name: String?,
    val project_name: String?,
    val store_name: String?,
    val added_by: String?,
    val materials: List<InvoiceMaterial>?,
    val invoice_number: String?,
    val added_on: String?,
    val vendor_name: String?,
)

data class MachineInvoice(
    val invoice_id: Int?,
    val organization_name: String?,
    val project_name: String?,
    val store_name: String?,
    val added_by: String?,
    val machines: List<InvoiceMachines>,
    val invoice_number: String?,
    val added_on: String?,
    val vendor_name: String?,
)

data class Machines(
    val machine_name: String?,
    val machine_ownership: String?,
    val machine_price: Int?,
    val machine_quantity: Int?,
)

data class GetMachineInvoiceResp(
    val invoice: MachineInvoice?,
    val status_code: String?
)

data class IndentResponse(
    val isSuccessful: Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val indent: List<Indent>?
)

data class AddMachineInvoice(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("organization_name") var organization_name: String?,
    @SerializedName("project_name") var project_name: String?,
    @SerializedName("invoice_number") var invoice_number: String?,
    @SerializedName("machines") var machines: JsonArray,
)


data class MachinesInvoice(
    @SerializedName("machine_name") var machine_name: String?,
    @SerializedName("machine_ownership") var machine_ownership: String?,
    @SerializedName("machine_price") var machine_price: String?,
    @SerializedName("machine_quantity") var machine_quantity: String?,
)

data class GetAllMachineInvoices(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("organization_name") var organization_name: String?,
    @SerializedName("project_name") var project_name: String?,
    @SerializedName("store_name") var store_name: String?

)

data class GetMacAndMatDispbyMe(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("organization_name") var organization_name: String?,
    @SerializedName("project_name") var project_name: String?,

    )

data class GetMachineInvoice(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("organization_name") var organization_name: String?,
    @SerializedName("project_name") var project_name: String?,
    @SerializedName("store_name") var store_name: String?,
    @SerializedName("invoice_id") var invoice_id: Int?,
)

data class DispatchMaterial(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("organization_name") var organization_name: String?,
    @SerializedName("project_name") var project_name: String?,
    @SerializedName("plan_name") var plan_name: String?,
    @SerializedName("store_name") var store_name: String?,
    @SerializedName("assigned_activity_id") var assigned_activity_id: Int?,
    @SerializedName("material_quantities") var material_quantities: LinkedHashMap<String, String>?,
    @SerializedName("dispatch_to") var dispatch_to: String?,
)


data class MaterialDispatches(
    val material_dispatches: List<MaterialDispatchResp>,
    val status_code: String?,
    val user: String?
)

data class MaterialDispatchResp(
    val mat_dispatch_id: Int?,
    val material_quantities: JsonObject,//array
    val project_name: String?,
    val organization_name: String?,
    val plan_name: String?,
    val assigned_activity_id: String?,
    val dispatched_by: String?,
    val dispatched_on: String?,
    val dispatched_to: String?,
    val status: String?,
    val approved_on: String?,
    val approved_by: String?,

    )

data class FetchMaterialDispatched(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("mat_dispatch_id") var mat_dispatch_id: String?
)

data class FetchMachineryDispatched(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("mach_dispatch_id") var mach_dispatch_id: String?
)

data class FetchMaterialDispatchedResp(
    val material_dispatches: MaterialDispatchResp?,
    val status_code: String?
)


data class DispatchMachinery(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("user_token") var user_token: String?,
    @SerializedName("organization_name") var organization_name: String?,
    @SerializedName("project_name") var project_name: String?,
    @SerializedName("plan_name") var plan_name: String?,
    @SerializedName("store_name") var store_name: String?,
    @SerializedName("assigned_activity_id") var assigned_activity_id: Int?,
    @SerializedName("machinery_quantities") var machinery_quantities: HashMap<String, Any>?,
    @SerializedName("dispatch_to") var dispatch_to: String?,
)

data class FetchMachineryDispatchedResp(
    val machinery_dispatches: MachineryDispatchResp?,
    val status_code: String?
)

data class MachineryDispatchResp(
    val mach_dispatch_id: Int?,
    val machinery_quantities: JSONObject,
    val project_name: String?,
    val organization_name: String?,
    val plan_name: String?,
    val assigned_activity_id: String?,
    val dispatched_by: String?,
    val dispatched_on: String?,
    val dispatched_to: String?,
    val status: String?,
    val approved_on: String?,
    val approved_by: String?,

    )

data class MachineryDispatches(
    val machinery_dispatches: List<MachineryDispatchResp>,
    val status_code: String?,
    val user: String?
)

data class ForgotPassword(
    @SerializedName("user_email") var user_email: String?,
    @SerializedName("token") var token: String?,
    @SerializedName("password") var password: String?
)


data class GetMachinesAndMaterials(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var prjct_name: String,
)


data class FetchAssignedMachAndMates(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var prjct_name: String,
)

data class GetAssignedMachinery(
    val assigned_activity_id: String,
    val activity_assigned_by: String,
    val activity_assigned_to: String,
    val machinery_assigned: JsonObject,
    val material_assigned: JsonObject,
)

data class GetListOfAssignedMachandMates(
    val status_code: String?,
    val assigned_machinery: List<GetAssignedMachinery>,
    val assigned_materials: List<GetAssignedMachinery>
)

data class MachineName(
    val machine_name: String?
)


data class FetchStoreMaterialDispatches(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("store_name") var store_name: String
)


data class GetStoreMachineryDispatch(
    val mat_dispatch_id: String,
    val mach_dispatch_id: String,
    val material_quantities: JsonObject,
    val machinery_quantities: JsonObject,
    val project_name: String,
    val organization_name: String,
    val plan_name: String,
    val store_name: String,
    val assigned_activity_id: String,
    val dispatched_by: String,
    val dispatched_on: String,
    val dispatched_to: String,
    val status: String,
    val approved_on: String,
    val approved_by: String,
)


data class GetListOfStoreMachineryDispatches(
    val status_code: String?,
    val material_dispatches: List<GetStoreMachineryDispatch>,
    val machinery_dispatches: List<GetStoreMachineryDispatch>
)