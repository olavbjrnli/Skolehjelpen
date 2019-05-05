package no.olavbjrnli.skolehjelpen.vgFragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import no.olavbjrnli.skolehjelpen.QuestionActivity
import no.olavbjrnli.skolehjelpen.R


class vg1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vg1, container, false)

        val naturfag : Button = view.findViewById(R.id.btnNaturfag) as Button
        val gym : Button = view.findViewById(R.id.btnGym) as Button
        val geografi : Button = view.findViewById(R.id.btnGeografi) as Button

        naturfag.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(naturfag.isPressed){
                intent.putExtra("naturfag pressed", true)
            }
            startActivity(intent)


        }
        gym.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(gym.isPressed){
                intent.putExtra("gym pressed", true)
            }
            startActivity(intent)

        }
        geografi.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(geografi.isPressed){
                intent.putExtra("geografi pressed", true)
            }
            startActivity(intent)

        }


        return view
    }
}
