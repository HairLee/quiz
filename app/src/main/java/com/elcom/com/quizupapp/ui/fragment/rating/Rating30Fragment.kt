package com.elcom.com.quizupapp.ui.fragment.rating

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.Statistical
import com.elcom.com.quizupapp.ui.adapter.SimpleStringRecyclerViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_rating30.*
import java.util.*

class Rating30Fragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_rating30, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updateLayout(data: List<Statistical>){
        recyclerview.layoutManager = LinearLayoutManager(recyclerview.context)
        recyclerview.adapter = SimpleStringRecyclerViewAdapter(activity!!,
                data)

        if (data.size >2){
            Picasso.get()
                    .load(data[0].url)
                    .into(imvAva1)

            tvNameTop1.text = data[0].name
            tvXp1.text = "Xp :"+ data[0].points

            Picasso.get()
                    .load(data[1].url)
                    .into(imvAva2)

            tvNameTop2.text = data[1].name
            tvXp2.text = "Xp :"+ data[1].points

            Picasso.get()
                    .load(data[2].url)
                    .into(imvAva3)

            tvNameTop3.text = data[2].name
            tvXp3.text = "Xp :"+ data[2].points


        } else {
            if(data.size == 1){
                Picasso.get()
                        .load(data[0].url)
                        .into(imvAva1)

                tvNameTop1.text = data[0].name
                tvXp1.text = "Xp :"+ data[0].points

            } else if (data.size == 2){
                Picasso.get()
                        .load(data[1].url)
                        .into(imvAva2)

                tvNameTop2.text = data[1].name
                tvXp2.text = "Xp :"+ data[1].points
            }
    }


    }

}
