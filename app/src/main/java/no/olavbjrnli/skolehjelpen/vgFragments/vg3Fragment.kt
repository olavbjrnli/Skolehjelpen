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


class vg3Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vg3, container, false)

        val engelsk : Button = view.findViewById(R.id.btnEngelsk) as Button
        val medie : Button = view.findViewById(R.id.btnMedie) as Button
        val religion : Button = view.findViewById(R.id.btnReligion) as Button

        engelsk.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(engelsk.isPressed){
                intent.putExtra("engelsk pressed", true)
            }
            startActivity(intent)


        }
        medie.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(medie.isPressed){
                intent.putExtra("medie pressed", true)
            }
            startActivity(intent)

        }
        religion.setOnClickListener{
            val intent = Intent(activity, QuestionActivity::class.java)
            if(religion.isPressed){
                intent.putExtra("religion pressed", true)
            }
            startActivity(intent)

        }


        return view
    }
}
