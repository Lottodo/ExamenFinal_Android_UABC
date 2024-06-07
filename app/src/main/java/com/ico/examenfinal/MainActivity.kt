package com.ico.examenfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ico.examenfinal.ui.details.DetailsViewModel
import com.ico.examenfinal.ui.main.MainScreen
import com.ico.examenfinal.ui.theme.ExamenFinalTheme
import com.ico.myapplication.ui.home.MainViewModel

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //var cast = detailsViewModel.castResponse
                    MainScreen(showsList = mainViewModel.showListResponse)
                    mainViewModel.getShowList()
                }
            }
        }
    }
}