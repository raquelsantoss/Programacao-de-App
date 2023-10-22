package com.example.desafio07

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.desafio07.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGetLocation.setOnClickListener {
            grantPermissions()
        }

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        enableGPS()
        getLocation()

    }

    private fun grantPermissions() {
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && (ActivityCompat.checkSelfPermission(
                applicationContext, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {

            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ), 101
            )
        }
    }

    private fun enableGPS() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        var networkEnabled = false

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("networkState", "gpsEnabled = $gpsEnabled")
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("networkState", "gpsEnabled = $networkEnabled")
        }

        if (!gpsEnabled && !networkEnabled) {
            AlertDialog.Builder(this)
                .setTitle("Enable GPS Service")
                .setMessage("Precisamos do acesso ao seu GPS.")
                .setCancelable(false)
                .setPositiveButton("Enable", DialogInterface.OnClickListener { dialog, which ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                .show();
        }

    }

    private fun getLocation() {
        try {
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                500,
                5f,
                (this as LocationListener)
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override fun onLocationChanged(location: Location) {
        try {
            val geocoder = Geocoder(applicationContext, Locale.getDefault())
            val address: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)

            binding.tvCountry.text = address[0].countryName
            binding.tvCountryCode.text = address[0].countryCode
            binding.tvState.text = address[0].adminArea
            binding.tvCity.text = address[0].locality
            binding.tvLocality.text = address[0].getAddressLine(0)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}