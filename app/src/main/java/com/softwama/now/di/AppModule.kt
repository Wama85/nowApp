package com.softwama.now.di

import com.softwama.now.features.login.data.LoginRepositoryImpl
import com.softwama.now.features.login.domain.repository.LoginRepository
import com.softwama.now.features.login.domain.usecase.LoginUseCase
import com.softwama.now.features.login.presentation.LoginViewModel
import com.softwama.now.features.olvidepass.data.OlvidepassRepositoryImpl
import com.softwama.now.features.olvidepass.domain.repository.OlvidepassRepository
import com.softwama.now.features.olvidepass.domain.usecase.OlvidepassUseCase
import com.softwama.now.features.olvidepass.presentation.OlvidepassViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    //repositorio
    single<LoginRepository>{ LoginRepositoryImpl() }
    single<OlvidepassRepository>{ OlvidepassRepositoryImpl() }

    //UseCase
    factory { LoginUseCase(get()) }
    factory { OlvidepassUseCase(get()) }

    //ViewModel
    viewModel { LoginViewModel(get())}
    viewModel { OlvidepassViewModel(get()) }
}