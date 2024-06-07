package com.ico.examenfinal.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ico.examenfinal.ui.theme.ExamenFinalTheme
import com.ico.myapplication.data.api.model.Show

class ShowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val show = intent.getSerializableExtra("show") as Show

        setContent {
            ExamenFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailsGUI(show)
                }
            }
        }
    }
}