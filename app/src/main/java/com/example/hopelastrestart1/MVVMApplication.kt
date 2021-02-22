package com.example.hopelastrestart1

import android.app.Application
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.NetworkConnectionInterceptor
import com.example.hopelastrestart1.data.preferences.PreferenceProvider
import com.example.hopelastrestart1.data.repositories.QuotesRepository
import com.example.hopelastrestart1.data.repository.*
import com.example.hopelastrestart1.ui.auth.AuthViewModelFactory
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanViewModelFactory
import com.example.hopelastrestart1.ui.home.organization.OrganizationViewModelFactory
import com.example.hopelastrestart1.ui.home.profile.ProfileViewModelFactory
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModelFactory
import com.example.hopelastrestart1.ui.home.project.tabs.StoreViewModelFactory
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModelFactory
import com.example.hopelastrestart1.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { ProjectRepository(instance(), instance()) }
        bind() from singleton { PlanRepository(instance(), instance()) }
        bind() from singleton { OrganizationUserRepository(instance(), instance()) }
        bind() from singleton { OrganizationStoreRepository(instance(), instance()) }
        bind() from singleton { OrganizationRepository(instance(), instance()) }
        bind() from singleton { TaskRepository(instance(), instance(), ) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider{ OrganizationViewModelFactory(instance())}
        bind() from provider{ ProjectViewModelFactory(instance()) }
        bind() from provider{ StoreViewModelFactory(instance()) }
        bind() from provider{ UserViewModelFactory(instance()) }
        bind() from provider{ PlanViewModelFactory(instance()) }
        bind() from provider{ TaskViewModelFactory(instance()) }
        bind() from provider{ QuotesViewModelFactory(instance())}

    }

}