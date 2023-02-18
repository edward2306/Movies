package com.example.domain.di

import com.example.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {

    factory { GenreListUseCase(get()) }
    factory { MovieGenreUseCase(get()) }
    factory { MovieReviewUseCase(get()) }
    factory { MovieUseCase(get()) }
    factory { MovieVideoUseCase(get()) }
}