package com.example.githubdemo.viewmodel

import androidx.lifecycle.ViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ViewModelProviderFactoryTest {

    @Test
    fun tesCreate_should_return_viewModel_from_the_map() {

        val viewModelMock = mockk<ViewModel>()
        val viewModelClass = ViewModel::class.java
        val map: Map<Class<*>, ViewModel> = mapOf(viewModelClass to viewModelMock)
        val factory = ViewModelProviderFactory(map)

        val result = factory.create(viewModelClass)

        assertEquals(result, viewModelMock)
    }

}