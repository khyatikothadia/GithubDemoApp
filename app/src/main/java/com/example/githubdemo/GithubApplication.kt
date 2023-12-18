package com.example.githubdemo

import android.app.Application
import com.example.githubdemo.di.component.ApplicationComponent
import com.example.githubdemo.di.component.DaggerApplicationComponent

class GithubApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}