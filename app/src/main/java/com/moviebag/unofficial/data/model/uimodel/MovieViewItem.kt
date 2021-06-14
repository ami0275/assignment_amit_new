package com.moviebag.unofficial.data.model.uimodel

import com.moviebag.unofficial.data.model.Movie
import kotlin.math.ln
import kotlin.math.pow

data class MovieViewItem(
    val id: Int,
    val imagePath: String,
    val title: String,
    val releaseDate: String,
    val votes: String,
    val popularity: String,
    val language: String
)

fun Movie.displayVoteCount(): String = this.voteAverage?.format().orEmpty().ifEmpty { "NA" }

// scale from 0-10 to 0-100%
fun Movie.displayDuration(): String = this.popularity?.format().orEmpty().ifEmpty { "NA" }

fun Double.format(): String {
    // source: https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp?lq=1
    if (this < 1000) return "${this}k"
    val exp = ln(this).div(ln(1000.0)).toInt()
    return String.format("%.2f %c", this.div(1000.0.pow(exp)), "kMGTPE"[exp - 1])
}

fun Int.formatHourMinutes(): String {
    val hours = this.div(60)
    val minutes = this % 60
    return String.format("%d: %02d: 00", hours, minutes)
}