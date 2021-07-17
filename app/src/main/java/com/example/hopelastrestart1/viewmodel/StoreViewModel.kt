package com.example.hopelastrestart1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.data.network.responses.*
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception
import java.util.HashMap

class StoreViewModel(private val apiService: ApiService) : ViewModel() {

    fun addStore(addStore: AddStore) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addStore(addStore)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getStores(getStore: GetStores) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getStores(getStore)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getMaterialInvoices(getAllMachineInvoices: GetAllMachineInvoices) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getMaterialInvoices(getAllMachineInvoices)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }
        }

    fun getMachinesInvoices(getAllMachineInvoices: GetAllMachineInvoices) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getMachineInvoices(getAllMachineInvoices)))
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


    fun addMachineInvocie(addMachineInvoice: AddMachineInvoice) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.addMachineInvoice(addMachineInvoice)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun addMaterialInvoice(addMaterialInvoice: AddMaterialInvoice) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.addMaterialInvoice(addMaterialInvoice)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getMaterialInvoice(getSingleInvoice: GetSingleInvoice) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getMaterialInvocie(getSingleInvoice)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getMachineInvoice(getSingleInvoice: GetSingleInvoice) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getMachineInvoice(getSingleInvoice)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


    fun fetchMaterialDispatchedByMe(fmdispatchedByMe: GetMacAndMatDispbyMe) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchMaterialsDispatchedByMe(
                            fmdispatchedByMe
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun fetchMaterialDispatchedToMe(fmdispatchedByMe: GetMacAndMatDispbyMe) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchMaterialsDispatchedToMe(
                            fmdispatchedByMe
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun fetchMachineryDispatchedByMe(fmdispatchedByMe: GetMacAndMatDispbyMe) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchMachineryDispatchedByMe(
                            fmdispatchedByMe
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun fetchMachineryDispatchedToMe(fmdispatchedByMe: GetMacAndMatDispbyMe) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchMachineryDispatchedToMe(
                            fmdispatchedByMe
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


    fun dispatchMaterial(dispatchMaterial: DispatchMaterial) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.dispatchMaterial(dispatchMaterial)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun dispatchMachinery(dispatchMachinery: DispatchMachinery) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.dispatchMachinery(dispatchMachinery)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


    fun getAssignedMachinery(fetchAssignedMachAndMates: FetchAssignedMachAndMates) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.getAssignedMachinery(
                            fetchAssignedMachAndMates
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun getAssignedMaterial(fetchAssignedMachAndMates: FetchAssignedMachAndMates) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.getAssignedMaterials(
                            fetchAssignedMachAndMates
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun fetchStoreMachineryDispatches(fetchStoreMaterialDispatches: FetchStoreMaterialDispatches) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchStoreMachineryDispatches(
                            fetchStoreMaterialDispatches
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


    fun fetchStoreMaterialDispatches(fetchStoreMaterialDispatches: FetchStoreMaterialDispatches) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchStoreMaterialDispatches(
                            fetchStoreMaterialDispatches
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun fetchMaterialDispatched(fetchMaterialDispatched: FetchMaterialDispatched) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchMaterialDispatched(
                            fetchMaterialDispatched
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun fetchMachineryDispatched(fetchMachineryDispatched: FetchMachineryDispatched) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.fetchMachineryDispatched(
                            fetchMachineryDispatched
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }


    fun approveMaterialDispatch(fetchMaterialDispatched: FetchMaterialDispatched) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.approveMaterialDispatch(
                            fetchMaterialDispatched
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }

    fun approveMachineryDispatch(fetchMachineryDispatched: FetchMachineryDispatched) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = apiService.approveMachineryDispatch(
                            fetchMachineryDispatched
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
            }

        }



}


