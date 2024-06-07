package com.ico.examenfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ico.examenfinal.ui.details.DetailsScreen
import com.ico.examenfinal.ui.details.DetailsViewModel
import com.ico.examenfinal.ui.theme.ExamenFinalTheme
import com.ico.myapplication.data.api.model.Show

class DetailsActivity : ComponentActivity() {
    val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val show = intent.getSerializableExtra("show") as Show
        val id = show.id

        setContent {
            ExamenFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var cast = detailsViewModel.castResponse
                    DetailsScreen(show = show, cast = cast)
                    detailsViewModel.getCast(id)
                }
            }
        }
    }
}