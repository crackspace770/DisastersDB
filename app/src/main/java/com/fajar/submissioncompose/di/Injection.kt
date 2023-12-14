package com.fajar.submissioncompose.di

import com.fajar.submissioncompose.data.DisasterRepository

object Injection {
    fun provideRepository(): DisasterRepository {
        return DisasterRepository.getInstance()
    }
}