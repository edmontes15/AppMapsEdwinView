package com.example.appmapsedwin.ui.theme

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appmapsedwin.R
import com.example.appmapsedwin.ui.theme.viewModel.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Calendar


class MainActivityMaps : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,
    LocationListener {
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var notificationManager: NotificationManager2
    private val handler = Handler()
    val currentDate = Calendar.getInstance().time

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_maps)
        createMapFragment()
        notificationManager = NotificationManager2(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]

    }

    private fun scheduleNotification() {
        val delayMillis = 5000 // 5000 milisegundos = 5 segundos
        handler.postDelayed({
            // Coloca aquí la lógica para enviar la notificación con latitud y longitud
            notificationManager.sendNotification(currentLatitude, currentLongitude, currentDate)
            sendLocationToServer()
            // Programa la siguiente notificación después de 5 segundos
            scheduleNotification()
        }, delayMillis.toLong())
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        if(isInternetAvailable(this)){
            if(isGPSEnabled(this)){
                scheduleNotification()
                createMarker()
                map.setOnMyLocationButtonClickListener(this)
                map.setOnMyLocationClickListener(this)
                enabledLocation()
            }else{
                showRetryDialog2()
            }
        }else{
            showRetryDialog()
        }
    }

    private fun showRetryDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sin conexión a Internet")
        builder.setMessage("No hay conexión a Internet. ¿Deseas volver a intentarlo?")
        builder.setPositiveButton("Sí") { dialog, which ->
            val intent = Intent(this, MainActivityMaps::class.java)
            finish() // Finaliza la actividad actual
            startActivity(intent)
        }
        builder.setNegativeButton("No") { _, _ ->
            finish()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showRetryDialog2() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No tienes activado la ubicacion")
        builder.setMessage("Prende tu ubicaion. ¿Deseas volver a intentarlo?")
        builder.setPositiveButton("Sí") { dialog, which ->
            val intent = Intent(this, MainActivityMaps::class.java)
            finish() // Finaliza la actividad actual
            startActivity(intent)
        }
        builder.setNegativeButton("No") { _, _ ->
            finish()
        }
        val dialog = builder.create()
        dialog.show()
    }




    // Esta función solicita permisos de notificación


    private fun createMarker() {
        if (isLocationPermissionGranted()) {

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        currentLatitude = it.latitude
                        currentLongitude = it.longitude
                        val currentLatLng = LatLng(currentLatitude, currentLongitude)
                        val motorcycleIcon =
                            BitmapDescriptorFactory.fromResource(R.drawable.ic_moto)
                        map.addMarker(
                            MarkerOptions().position(currentLatLng).title("Mi ubicación actual")
                                .icon(motorcycleIcon)
                        )
                        map.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(currentLatLng, 20f),
                            4000,
                            null
                        )


                    }
                }
        } else {
            //requestLocationPermission()
        }
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    private fun enabledLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermision()
        }
    }

    private fun requestLocationPermision() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "ve a ajusted y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localizacion ve a ajusted y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermissionGranted()) {
            map.isMyLocationEnabled = false
            Toast.makeText(
                this,
                "Para activar la localizacion ve a ajusted y acepta los permisos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(
            this,
            "Para activar la localizacion ve a ajusted y acepta los permisos",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estas en ${p0.latitude}, ${p0.altitude}", Toast.LENGTH_SHORT).show()

    }

    override fun onLocationChanged(location: Location) {
    }

    private fun sendLocationToServer() {
        // Llama a la función en el ViewModel para enviar la ubicación
        locationViewModel.sendLocation(currentLatitude, currentLongitude, currentDate)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun isGPSEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}