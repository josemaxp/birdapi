package com.josemaxp.birdpedy.functions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.josemaxp.birdpedy.R


//Texto
fun generalFontFamily(): FontFamily{
    return FontFamily (Font(R.font.opensans_regular))
}

fun generalScientificNameFontFamily(): FontFamily{
    return FontFamily (Font(R.font.opensans_bolditalic))
}

fun generalBoldFontFamily(): FontFamily{
    return FontFamily (Font(R.font.opensans_bold))
}

@Composable
fun generalFontStyle(): TextStyle {
    return MaterialTheme.typography.bodyMedium
}
@Composable
fun generalScientificNameFontStyle(): TextStyle {
    return MaterialTheme.typography.bodySmall
}

@Composable
fun generalTitleFontSize(): TextStyle {
    return MaterialTheme.typography.titleLarge
}
