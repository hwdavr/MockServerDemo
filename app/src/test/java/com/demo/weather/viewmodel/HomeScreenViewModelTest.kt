package com.demo.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.weather.model.City
import com.demo.weather.repository.QueryCityRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeScreenViewModelTest {
    private lateinit var  queryRepo: QueryCityRepo
    private lateinit var  viewModel: HomeScreenViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
        queryRepo = Mockito.mock(QueryCityRepo::class.java)
        viewModel = HomeScreenViewModel(queryRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun queryCityList() = runBlocking {
        Mockito.lenient().`when`(queryRepo.queryCities(ArgumentMatchers.anyString())).thenReturn(
            flowOf(
                listOf(
                    City("City 1", 0),
                    City("City 2", 2)
                )
            )
        )

        viewModel.queryCityList("Test")

        delay(1_000)
        assertTrue(viewModel.cities.value?.size ?: 0 > 0)
        print("Get list size ${viewModel.cities.value?.size}")
    }
}
