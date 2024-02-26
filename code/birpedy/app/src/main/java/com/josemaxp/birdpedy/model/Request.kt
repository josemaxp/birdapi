package com.josemaxp.birdpedy.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.josemaxp.birdpedy.dao.birds.Bird
import com.josemaxp.birdpedy.dao.birds.BirdViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


fun insertData(birdViewModel: BirdViewModel, allBirds: List<Bird>, loadData: MutableState<Boolean>) {
    try {
        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = URL("https://josemaxp.github.io/birdapi/data.json").readText()

            val type = object : TypeToken<List<Bird>>() {}.type
            val birdList = Gson().fromJson<List<Bird>>(apiResponse, type)

            if (allBirds.isEmpty()) {
                for (bird in birdList) {
                    birdViewModel.insert(bird)
                }
            } else {
                if (allBirds.size == birdList.size && allBirds.sortedBy { it.id } != birdList) {
                    for (bird in birdList) {
                        birdViewModel.update(bird)
                    }
                    loadData.value = true
                }
            }
            cancel()
        }
    }catch (e: Exception){
        Log.i("Error", e.message.toString())
    }
}

fun loadData(birdViewModel: BirdViewModel, allBirds: State<List<Bird>>, loadData: MutableState<Boolean>){
    try {
        val allBirdsId = allBirds.value.map { it.id }
        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = URL("https://josemaxp.github.io/birdapi/data.json").readText()

            val type = object : TypeToken<List<Bird>>() {}.type
            val birdList = Gson().fromJson<List<Bird>>(apiResponse, type)

            for (bird in birdList) {
                if(bird.id in allBirdsId) {
                    birdViewModel.update(bird)
                }else{
                    birdViewModel.insert(bird)
                }
            }

            withContext(Dispatchers.Main){
                loadData.value = true
            }

            cancel()
        }
    }catch (e: Exception){
        Log.i("Error", e.message.toString())
    }
}


/*val jsonArray = JSONArray(apiResponse)
for (i in 0 until jsonArray.length()) {
    val jsonObject = jsonArray.getJSONObject(i)
    val id = jsonObject.getInt("id")
    val commonName = jsonObject.getString("nombre_comun")
    val scientificName = jsonObject.getString("nombre_cientifico")
    val jpg = jsonObject.getString("jpg")
    val png = jsonObject.getString("png")
    val reference = jsonObject.getString("referencia")

    val bird = Bird(id, commonName, scientificName, jpg, png, reference)
    birdViewModel.insert(bird)
}*/