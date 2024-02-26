package com.josemaxp.birdpedy

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatchViewModel
import com.josemaxp.birdpedy.dao.birds.BirdViewModel
import com.josemaxp.birdpedy.dao.families.FamilyViewModel
import com.josemaxp.birdpedy.dao.order.OrderViewModel
import com.josemaxp.birdpedy.dao.users.UserViewModel
import com.josemaxp.birdpedy.ui.navigation.AppNavigation
import com.josemaxp.birdpedy.ui.theme.BirdPedyTheme

class MainActivity : ComponentActivity() {

    /*private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.FLEXIBLE*/

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        if(updateType == AppUpdateType.FLEXIBLE){
            appUpdateManager.registerListener(installStateUpdateListener)
        }
        checkForAppUpdates()*/

        setContent {
            BirdPedyTheme {
                val models = remember { mutableStateOf(mapOf<String, AndroidViewModel>()) }
                val birdViewModel: BirdViewModel by viewModels()
                val birdWatchViewModel: BirdWatchViewModel by viewModels()
                val userViewModel: UserViewModel by viewModels()
                val familyViewModel: FamilyViewModel by viewModels()
                val orderViewModel: OrderViewModel by viewModels()

                models.value = mapOf(
                    "bird" to birdViewModel,
                    "birdWatch" to birdWatchViewModel,
                    "user" to userViewModel,
                    "family" to familyViewModel,
                    "order" to orderViewModel
                )

                AppNavigation(models.value)
            }
        }
    }

    /*private fun checkForAppUpdates(){
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            val isUpdateAvailable = info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            val isUpdateAllowed =  info.isUpdateTypeAllowed(updateType)

            if(isUpdateAllowed && isUpdateAvailable){
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateType,
                    this,
                    123
                )
            }
        }
    }

    private val installStateUpdateListener = InstallStateUpdatedListener{ state ->
        if(state.installStatus() == InstallStatus.DOWNLOADED){
            Toast.makeText(
                applicationContext,
                "Descarga existosa. La aplicación se reiniciará en unos instantes.",
                Toast.LENGTH_LONG
            ).show()
            lifecycleScope.launch {
                delay(5.seconds)
                appUpdateManager.completeUpdate()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(updateType == AppUpdateType.IMMEDIATE) {
            appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
                if (info.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    appUpdateManager.startUpdateFlowForResult(
                        info,
                        updateType,
                        this,
                        123
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123){
            if(resultCode != RESULT_OK){
                println("Error al actualizar la aplicación")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(updateType == AppUpdateType.FLEXIBLE){
            appUpdateManager.unregisterListener(installStateUpdateListener)
        }
    }*/
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}