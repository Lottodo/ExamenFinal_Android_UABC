package com.ico.examenfinal.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ico.examenfinal.data.api.model.Cast
import com.ico.myapplication.data.api.ShowApi
import com.ico.myapplication.data.api.model.Show
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class DetailsViewModel: ViewModel() {
    var castResponse: Cast? by mutableStateOf(null)
    var errorMessage: String by mutableStateOf("")
    fun getCast(id: Int) {
        viewModelScope.launch {
            val apiService = ShowApi.getInstance()
            try {
                val cast = apiService.getCast(id.toString())
                castResponse = cast
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

fun boldTitleRegularText(title: String, regular: String): AnnotatedString {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(title)
        }
        append(regular)
    }

    return text
}

fun getGenresString(show: Show): String {
    var genresString = ""
    for ((index, item) in show.genres.withIndex()) {
        if (index == show.genres.size - 1) {
            genresString += item
        }
        else
            genresString = "$genresString$item, "
    }

    return genresString
}

fun formatedDate(input: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)

    val date = LocalDate.parse(input, inputFormatter)
    return date.format(outputFormatter)
}