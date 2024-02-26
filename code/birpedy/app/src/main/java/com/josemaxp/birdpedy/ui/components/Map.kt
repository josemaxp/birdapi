package com.josemaxp.birdpedy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PatternItem
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CustomizeMap(modifier: Modifier = Modifier){
    val context = LocalContext.current
    val spain = LatLng(40.416775, -3.703790)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(spain, 5.5f)
    }


    Box(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .matchParentSize()
                .background(Black.copy(alpha = 0.6f)),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.TERRAIN,
                //mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle)
            )
        ) {
            Circle(
                center = cameraPositionState.position.target,
                radius = 20000.0,
                strokeWidth = 20f,
                strokeColor = Color(0xff0f9d58),
                strokePattern = listOf(
                    PatternItem(1, cameraPositionState.position.zoom * 10),
                    PatternItem(2, cameraPositionState.position.zoom)
                )
            )
        }
    }
}

