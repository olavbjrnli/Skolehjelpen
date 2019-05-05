package no.olavbjrnli.skolehjelpen.databaseClasses

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_settings.*
import no.olavbjrnli.skolehjelpen.MenuActivity
import no.olavbjrnli.skolehjelpen.R

class SettingsActivity : AppCompatActivity(), SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    //Made with translation from Java to Kotlin from https://www.youtube.com/watch?v=D-qfVHvNQ8M. No credit taken
    override fun onSensorChanged(event: SensorEvent?) {
        var aceVal = SensorManager.GRAVITY_EARTH
        var aceLast = SensorManager.GRAVITY_EARTH
        var shake = 0.00f

        val x = event!!.values[0]
        val y = event!!.values[1]
        val z = event!!.values[2]

        aceLast = aceVal
        aceVal = Math.sqrt(((x*x + y*y + z*z).toDouble())).toFloat()
        var delta = aceVal - aceLast
        shake = shake * 0.9f + delta
        if(shake > 12) {
           showRes.text = "Hva gjør PCer når de skal på byen? Svar: De setter på sjarmen"
        }

    }

    //Navbar from https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_back -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private lateinit var sManager : SensorManager
    //Source for Inserting and getting database input https://github.com/kmvignesh/SqliteExample/tree/master/app/src/main/java/com/example/vicky/sqliteexample
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        btnPress()

        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun btnPress() {
        val context = this
        val db = DataBaseHandler(context)
        btnInsert.setOnClickListener() {
            if (dbName.text.toString().isNotEmpty() &&
                dbAge.text.toString().isNotEmpty()
            ) {
                val user = User(dbName.text.toString(), dbAge.text.toString().toInt())
                db.insertData(user)
                Handler().postDelayed({

                    val data = db.readData()
                    showRes.text = ""
                    for (i in 0..data.size - 1) {
                        showRes.append(data[i].name + " " + data[i].age + " år ble registrert")
                    }


                }, 1000)
            } else {
                showRes.text = "Du la ikke inn noen informasjon"
            }
        }
    }
}
