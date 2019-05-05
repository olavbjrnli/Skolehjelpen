package no.olavbjrnli.skolehjelpen

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_question.*
import no.olavbjrnli.skolehjelpen.databaseClasses.Question
import no.olavbjrnli.skolehjelpen.databaseClasses.SettingsActivity
import no.olavbjrnli.skolehjelpen.vgFragments.vg1Fragment

class QuestionActivity : AppCompatActivity() {
    private lateinit var btnOne : Button
    private lateinit var btnTwo : Button
    private lateinit var btnThree : Button
    private lateinit var btnFour : Button
    private lateinit var questionText : TextView
    private lateinit var database: DatabaseReference
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
                /*if(intent.getBooleanExtra("naturfag pressed", false) || intent.getBooleanExtra("gym pressed", false)
                    || intent.getBooleanExtra("geografi pressed", false)){
                    val intent = Intent(this, vg1Fragment::class.java)
                    startActivity(intent)
                }*/
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        btnOne = findViewById(R.id.btn1)
        btnTwo = findViewById(R.id.btn2)
        btnThree = findViewById(R.id.btn3)
        btnFour = findViewById(R.id.btn4)
        questionText = findViewById(R.id.txtQuestion)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        getQuestions()
    }
    var total = 0
    var correct = 0
    var wrong = 0
    /*This method is done with inspiration and help from https://www.youtube.com/watch?v=SiGIAvMH2Dk
      and https://www.raywenderlich.com/5114-firebase-tutorial-for-android-getting-started. No git repo to link*/
    private fun getQuestions() {
        total++
        val correctSound = MediaPlayer.create(this, R.raw.correct)
        val wrongSound = MediaPlayer.create(this, R.raw.wrong)
        if(total > 5){
            btnOne.isEnabled = false
            btnTwo.isEnabled = false
            btnThree.isEnabled = false
            btnFour.isEnabled = false
            questionText.text = "Quiz fullført!"
            txtCorrect.text = "Du fikk " + correct.toString() + " riktig og " + wrong.toString() +  " feil"
        }else {
            if(intent.getBooleanExtra("naturfag pressed", false)) {
                database = FirebaseDatabase.getInstance().reference.child("Naturfag").child(total.toString())
            }
            if(intent.getBooleanExtra("gym pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Gym").child(total.toString())
            }
            if(intent.getBooleanExtra("geografi pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Geografi").child(total.toString())
            }
            if(intent.getBooleanExtra("religion pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Religion").child(total.toString())
            }
            if(intent.getBooleanExtra("historie pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Historie").child(total.toString())
            }
            if(intent.getBooleanExtra("spansk pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Spansk").child(total.toString())
            }
            if(intent.getBooleanExtra("norsk pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Geografi").child(total.toString())
            }
            if(intent.getBooleanExtra("engelsk pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Geografi").child(total.toString())
            }
            if(intent.getBooleanExtra("medie pressed", false)){
                database = FirebaseDatabase.getInstance().reference.child("Geografi").child(total.toString())
            }

            val listener = object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                }
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val questions: Question? = dataSnapshot.getValue(Question::class.java)
                    questionText.text = questions?.Question
                    btnOne.text = questions?.option1
                    btnTwo.text = questions?.option2
                    btnThree.text = questions?.option3
                    btnFour.text = questions?.option4

                    btnOne.setOnClickListener {
                        if (btnOne.text.toString() == questions?.answer) {
                            btnOne.setBackgroundColor(Color.parseColor("#00cc66"))
                            correct++
                            correctSound.start()
                            Handler().postDelayed({
                                    btnOne.setBackgroundColor(Color.WHITE)
                                    getQuestions()
                            }, 1500)

                        } else {
                            wrong++
                            wrongSound.start()
                            btnOne.setBackgroundColor(Color.RED)
                            if (btnTwo.text.toString() == questions?.answer) {
                                btnTwo.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnThree.text.toString() == questions?.answer) {
                                btnThree.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnFour.text.toString() == questions?.answer) {
                                btnFour.setBackgroundColor(Color.parseColor("#00cc66"))
                            }

                            val handler = Handler()
                            handler.postDelayed( {
                                btnOne.setBackgroundColor(Color.WHITE)
                                btnTwo.setBackgroundColor(Color.WHITE)
                                btnThree.setBackgroundColor(Color.WHITE)
                                btnFour.setBackgroundColor(Color.WHITE)
                                getQuestions()
                            }, 1500)

                        }
                    }
                    btnTwo.setOnClickListener {
                        if (btnTwo.text.toString() == questions?.answer) {
                            btnTwo.setBackgroundColor(Color.parseColor("#00cc66"))
                            correct++
                            correctSound.start()
                            Handler().postDelayed({
                                btnTwo.setBackgroundColor(Color.WHITE)
                                getQuestions()

                            }, 1500)

                        } else {
                            wrong++
                            wrongSound.start()
                            btnTwo.setBackgroundColor(Color.RED)
                            if (btnOne.text.toString() == questions?.answer) {
                                btnOne.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnThree.text.toString() == questions?.answer) {
                                btnThree.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnFour.text.toString() == questions?.answer) {
                                btnFour.setBackgroundColor(Color.parseColor("#00cc66"))
                            }

                            val handler = Handler()
                            handler.postDelayed( {
                                btnOne.setBackgroundColor(Color.WHITE)
                                btnTwo.setBackgroundColor(Color.WHITE)
                                btnThree.setBackgroundColor(Color.WHITE)
                                btnFour.setBackgroundColor(Color.WHITE)
                                getQuestions()
                            }, 1500)

                        }
                    }
                    btnThree.setOnClickListener {
                        if (btnThree.text.toString() == questions?.answer) {
                            btnThree.setBackgroundColor(Color.parseColor("#00cc66"))
                            correct++
                            correctSound.start()
                            Handler().postDelayed({
                                btnThree.setBackgroundColor(Color.WHITE)
                                getQuestions()

                            }, 1500)

                        } else {
                            wrong++
                            wrongSound.start()
                            btnThree.setBackgroundColor(Color.RED)
                            if (btnTwo.text.toString() == questions?.answer) {
                                btnTwo.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnOne.text.toString() == questions?.answer) {
                                btnOne.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnFour.text.toString() == questions?.answer) {
                                btnFour.setBackgroundColor(Color.parseColor("#00cc66"))
                            }

                            val handler = Handler()
                            handler.postDelayed( {
                                btnOne.setBackgroundColor(Color.WHITE)
                                btnTwo.setBackgroundColor(Color.WHITE)
                                btnThree.setBackgroundColor(Color.WHITE)
                                btnFour.setBackgroundColor(Color.WHITE)
                                getQuestions()
                            }, 1500)

                        }
                    }
                    btnFour.setOnClickListener {
                        if (btnFour.text.toString() == questions?.answer) {
                            btnFour.setBackgroundColor(Color.parseColor("#00cc66"))
                            correct++
                            correctSound.start()
                            Handler().postDelayed({
                                btnFour.setBackgroundColor(Color.WHITE)
                                getQuestions()

                            }, 1500)

                        } else {
                            wrong++
                            wrongSound.start()
                            btnFour.setBackgroundColor(Color.RED)
                            if (btnTwo.text.toString() == questions?.answer) {
                                btnTwo.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnThree.text.toString() == questions?.answer) {
                                btnThree.setBackgroundColor(Color.parseColor("#00cc66"))
                            }
                            if (btnOne.text.toString() == questions?.answer) {
                                btnOne.setBackgroundColor(Color.parseColor("#00cc66"))
                            }

                            val handler = Handler()
                            handler.postDelayed( {
                                btnOne.setBackgroundColor(Color.WHITE)
                                btnTwo.setBackgroundColor(Color.WHITE)
                                btnThree.setBackgroundColor(Color.WHITE)
                                btnFour.setBackgroundColor(Color.WHITE)
                                getQuestions()
                            }, 1500)

                        }
                    }
                }
            }
            database.addListenerForSingleValueEvent(listener)

        }
    }
}









