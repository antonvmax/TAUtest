package pro.antonvmax.tautest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isServicesOK()) {
            init()
        }
    }

    fun init() {
        val btnMap = findViewById(R.id.btnMap) as Button
        btnMap.setOnClickListener(View.OnClickListener {
            intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        })
    }

    fun isServicesOK(): Boolean {
        Log.d(TAG, "isServicesOK: check google services version")

        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServicesOK: Google Play services is working")
            return true
        }
        if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOK: error we can fix")
            val dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST)
            dialog.show()
        }
        Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show()
        return false
    }

    companion object {
        const val TAG = "MainActivity"
        const val ERROR_DIALOG_REQUEST = 9001
    }
}
