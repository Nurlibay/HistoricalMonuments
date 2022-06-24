package uz.texnopos.historicalmonuments.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.texnopos.historicalmonuments.data.MonumentDatabase
import uz.texnopos.historicalmonuments.domain.MainRepository
import uz.texnopos.historicalmonuments.domain.MainRepositoryImpl
import uz.texnopos.historicalmonuments.presenter.search.SearchListViewModel
import uz.texnopos.historicalmonuments.presenter.main.MainViewModel

val roomModule = module {
    single { MonumentDatabase.getInstance(androidContext()).dao() }
}

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(dao = get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(mainRepository = get()) }
    viewModel { SearchListViewModel(mainRepository = get()) }
}
