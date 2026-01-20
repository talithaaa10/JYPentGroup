package com.example.jypentgroup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_GROUP = "extra_group"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvDescription: TextView = findViewById(R.id.tv_detail_description)
        val rvMembers: RecyclerView = findViewById(R.id.rv_members)
        val btnShare: Button = findViewById(R.id.action_share)

        // 1. Tangkap Data Grup dari Intent
        val group = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_GROUP, Group::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_GROUP)
        }

        if (group != null) {
            tvName.text = group.name
            tvDescription.text = group.description
            imgPhoto.setImageResource(group.photo)
            rvMembers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            val listMember = getMembersData(group.name)

            val memberAdapter = ListMemberAdapter(listMember)
            rvMembers.adapter = memberAdapter

            btnShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Lihat grup keren ini: ${group.name}\n\n${group.description}")
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }

    private fun getMembersData(groupName: String): ArrayList<Member> {
        val list = ArrayList<Member>()

        when (groupName) {
            "ITZY" -> {
                list.add(Member("Yeji", "Leader", R.drawable.yeji))
                list.add(Member("Lia", "Main Vocal", R.drawable.lia))
                list.add(Member("Ryujin", "Main Rapper", R.drawable.ryujin))
                list.add(Member("Chaeryeong", "Main Dancer", R.drawable.chaeryeong))
                list.add(Member("Yuna", "Visual", R.drawable.yuna))
            }
            "Stray Kids" -> {
                list.add(Member("Cristoper Bang", "Cristoper Bang", R.drawable.chan))
                list.add(Member("Lee Know", "Dancer", R.drawable.leeknow))
                list.add(Member("Seo Changbin", "Rapper", R.drawable.changbin))
                list.add(Member("Hyunjin", "Dancer", R.drawable.hyunjin))
                list.add(Member("Kim Seungmin", "Vokalist", R.drawable.seungmin))
                list.add(Member("Han Jisung", "Rapper", R.drawable.han))
                list.add(Member("Lee Felix", "Rapper", R.drawable.felix))
                list.add(Member("Jeong In", "Dancer", R.drawable.inn))
            }
            "TWICE" -> {
                list.add(Member("Jihyo", "Leader", R.drawable.jihyo))
                list.add(Member("Nayeon", "Center", R.drawable.nayeon))
                list.add(Member("Jeongyeon", "Lead vokalist", R.drawable.jeongyeon))
                list.add(Member("Momo", "Main Dancer", R.drawable.momo))
                list.add(Member("Sana", "Sub Vokalist", R.drawable.sana))
                list.add(Member("Mina", "Main Dancer", R.drawable.mina))
                list.add(Member("Dahyun", "Lead Rapper", R.drawable.dahyun))
                list.add(Member("Chaeyong", "Main Rapper", R.drawable.chaeyoung))
                list.add(Member("Tyuzu", "Lead Dancer", R.drawable.tyuzu))
            }
            "NMIXX" -> {
                list.add(Member("Haewon", "Leader", R.drawable.haewon))
                list.add(Member("Bae Jinsol", "Center", R.drawable.bae))
                list.add(Member("Sullyoon", "Lead vokalist", R.drawable.sullyoon))
                list.add(Member("Lily", "Main Dancer", R.drawable.lily))
                list.add(Member("Jiwoo", "Sub Vokalist", R.drawable.jiwoo))
                list.add(Member("Kyujin", "Main Dancer", R.drawable.kyujin))
            }

            "DAY6" -> {
                list.add(Member("Park Sung-jin", "Leader, Main Vocalist, Rhythm Guitarist", R.drawable.sungjin))
                list.add(Member("Kang Young-hyun", "Main Rapper, Main Vocalist, Bassist", R.drawable.youngk))
                list.add(Member("Kim Won-Pil", "Main Vocalist, Keyboardist, Synthesizer, Visual", R.drawable.wonpil))
                list.add(Member("Yoon Do-woon", "Drummer, Vocalist, Maknae", R.drawable.dowoon))
            }
            "Xdinary Heroes" -> {
                list.add(Member("Goo Gun-il", "Leader, Drummer", R.drawable.gunil))
                list.add(Member("Kim Jung-su", "Keyboardist, Main Vocalist", R.drawable.jungsu))
                list.add(Member("Kwak Ji-seok", "Rhythm Guitarist, Rapper, Vocalist", R.drawable.gaon))
                list.add(Member("Oh Seung-min", "Synthesizer, Rapper, Vocalist", R.drawable.ode))
                list.add(Member("Han Hyeong-jun", "Lead Guitarist", R.drawable.junhan))
                list.add(Member("Lee Joo-yeon", "Bassist, Main Vocalist, Visual, Maknae", R.drawable.jooyeon))
            }
            "2PM" -> {
                list.add(Member("Kim Min Ju", "Main Vokalist", R.drawable.junk))
                list.add(Member("Nichkhun", "Vocalist, Rapper, Visual", R.drawable.nichkhun))
                list.add(Member("Ok Taec Yeon", "Main Rapper, Sub-vocalist, 2nd Visual", R.drawable.taecyeon))
                list.add(Member("Jang Woo Young", "Main Dancer, Lead Vocalist", R.drawable.wooyoung))
                list.add(Member("Lee Junho", "Main vocalist, Lead Dancer", R.drawable.junho))
                list.add(Member("Hwang Chan Sung", "Lead Rapper, Vocalist, Maknae", R.drawable.chansung))
            }
            "NiziU" -> {
                list.add(Member("Mako", "Leader", R.drawable.mako))
                list.add(Member("Rio", "Main Dancer", R.drawable.rio))
                list.add(Member("Maya", "Lead Dancer", R.drawable.maya))
                list.add(Member("Riku", "Lead Vocalist", R.drawable.riku))
                list.add(Member("Ayaka", "Visual", R.drawable.ayaka))
                list.add(Member("Mayuka", "Lead Rapper", R.drawable.mayuka))
                list.add(Member("Rima", "Main Rapper", R.drawable.rima))
                list.add(Member("Miihi", "Lead Vocalist", R.drawable.miihi))
                list.add(Member("Nina", "Main Vocalist", R.drawable.nina))
            }
            "NEXZ" -> {
                list.add(Member("Tomoya", "Leader, All-Rounder", R.drawable.tomoya))
                list.add(Member("Haru", "Main Dancer", R.drawable.haru))
                list.add(Member("Yuki", "Main Vocal", R.drawable.yuki))
                list.add(Member("Ken", "Vocalist", R.drawable.sogeon))
                list.add(Member("Yu", "Dancer, Vocal", R.drawable.yu))
                list.add(Member("Yuhi", "Rapper, Maknae", R.drawable.hyui))
                list.add(Member("Seita", "Rapper, Visual", R.drawable.seita))
            }

            "Kickflip" -> {
                list.add(Member("Lee Gye-hun", "Leader", R.drawable.kyeehoon))
                list.add(Member("Lee Dong-hwa", "Dancer", R.drawable.donghwa))
                list.add(Member("Mitsuyuki Amaru", "Main Vocalist", R.drawable.amaru))
                list.add(Member("Jang Ju-wang", "Main Vocalist", R.drawable.juwang))
                list.add(Member("Choi Min-je", "Rapper, Dancer", R.drawable.minje))
                list.add(Member("Okamoto Keiju", "Main Rapper", R.drawable.keiju))
                list.add(Member("Lee Dong-hyeon", "Main Vocalist, Maknae", R.drawable.donghyeon))
            }
        }
        return list
    }
}