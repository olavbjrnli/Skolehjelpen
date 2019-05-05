package no.olavbjrnli.skolehjelpen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.Button
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.btnVG2
import kotlinx.android.synthetic.main.activity_menu.btnVG3
import no.olavbjrnli.skolehjelpen.databaseClasses.SettingsActivity
import no.olavbjrnli.skolehjelpen.vgFragments.vg1Fragment
import no.olavbjrnli.skolehjelpen.vgFragments.vg2Fragment
import no.olavbjrnli.skolehjelpen.vgFragments.vg3Fragment

class MenuActivity : AppCompatActivity() {
    //navbar from https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val vg1 : Button = findViewById(R.id.btnVG1)
        val vg2 : Button = findViewById(R.id.btnVG2)
        val vg3 : Button = findViewById(R.id.btnVG3)

        vg1.setOnClickListener(){
            val fragment = vg1Fragment()

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.start_frame,fragment).commit()
            btnVG1.isEnabled = false
            btnVG2.isEnabled = false
            btnVG3.isEnabled = false
        }

        vg2.setOnClickListener(){
            val fragment = vg2Fragment()

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.start_frame,fragment).commit()
            btnVG1.isEnabled = false
            btnVG2.isEnabled = false
            btnVG3.isEnabled = false
        }
        vg3.setOnClickListener(){
            val fragment = vg3Fragment()

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.start_frame,fragment).commit()
            btnVG1.isEnabled = false
            btnVG2.isEnabled = false
            btnVG3.isEnabled = false
        }

    }



}
