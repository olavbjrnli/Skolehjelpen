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


class vg2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vg2, container, false)

        val historie : Button = view.findViewById(R.id.btnHistorie) as Button
        val norsk : Button = view.findViewById(R.id.btnNorsk) as Button
        val spansk : Button = view.findViewById(R.id.btnSpansk) as Button

        historie.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(historie.isPressed){
                intent.putExtra("historie pressed", true)
            }
            startActivity(intent)


        }
        norsk.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(norsk.isPressed){
                intent.putExtra("norsk pressed", true)
            }
            startActivity(intent)

        }
        spansk.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(spansk.isPressed){
                intent.putExtra("spansk pressed", true)
            }
            startActivity(intent)

        }


        return view
    }
}
