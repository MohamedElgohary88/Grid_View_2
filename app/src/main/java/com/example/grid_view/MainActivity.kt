package com.example.grid_view

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.delete.view.*
import kotlinx.android.synthetic.main.single_row.view.*

class MainActivity : AppCompatActivity() {

    var images : ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        images = ArrayList()

        images!!.add(R.drawable.android_1)
        images!!.add(R.drawable.bird_30)
        images!!.add(R.drawable.frindes_0)
        images!!.add(R.drawable.profile_1)
        images!!.add(R.drawable.profile_11)
        images!!.add(R.drawable.profile_13)
        images!!.add(R.drawable.profile_14)
        images!!.add(R.drawable.profile_2)
        images!!.add(R.drawable.profile_3)
        images!!.add(R.drawable.profile_4)
        images!!.add(R.drawable.profile_5)
        images!!.add(R.drawable.profile_6)
        images!!.add(R.drawable.profile_8)
        images!!.add(R.drawable.windows_10)
        images!!.add(R.drawable.windows_10_10)
        images!!.add(R.drawable.windows_11)
        images!!.add(R.drawable.windows__10)

        // ده متغير اسمه gridAdapter بيرث من كلاس GridAdapter وبياخد 2 باراميتر
        val gridAdapter = GridAdapter(images!!, this)
        // بعد كده ده ندهناه على الاى دى بتاع gridView خليناه بيساوى gridAdapter
        gridView.adapter =gridAdapter
        //هنا بقى عملنا لل gridView Listener وخليناه يساى adapter بياخد 4 باراميتر
        gridView.onItemLongClickListener= (AdapterView.OnItemLongClickListener{ parent, view, position, id ->
            //  بعد كده ده AlertDialog عشان يظهرلك view عندما تضغط على الصورة
            // هو المسئول عن ذلك
            val alertBuilder = AlertDialog.Builder(this)
            // inflater عشان يحول الزر من اكس ام ال  الى عنصر فى الكوتلن
            // هنا عملناه
            var inflater:LayoutInflater= layoutInflater
            val view = inflater.inflate(R.layout.delete,null)
            // هنا لكى يظهر
            alertBuilder.setView(view)
            // هنا نستدعى دالة الانشاء create
            val alertDialog = alertBuilder.create()
            alertBuilder.setTitle("remove image ?")
            alertDialog.show()
            // هنا عشان لما يضغط على delete يمسح الصورة
            view.btndelete.setOnClickListener {
                val myImage = images!![position]
                gridAdapter.gridImages.remove(myImage)
                gridAdapter.notifyDataSetChanged()
                alertDialog.dismiss()
            }
            false
        })
    }
}
// لابد من  استخدام BaseAdapter مع GridAdapter
// كما فعلنا مع listview
class GridAdapter(var gridImages:ArrayList<Int>, private val context: Context) :BaseAdapter(){

    // يوجد اربع دوال تم عمل implement لهم من داخل كلاس BaseAdapter
 // الدالة الاولى وهى getView تم شرحها مع listview
    // ويتم استدعائها كل مرة عندما يتم عرض عنصر جديد
    override fun getView(position: Int, convertView:View?, parent:ViewGroup?): View {
    var view = convertView
    if (view == null){
        view = LayoutInflater.from(context).inflate(R.layout.single_row ,parent,false)

    }
        var myImage = gridImages[position]
        view!!.imageView.setImageResource(gridImages[position])
        return view

    }
//  الدالة الثانية getItem
    // وهى مسئولة عن العناصر التى يتم عرضها
    override fun getItem(position:Int): Any {
        TODO("Not yet implemented")
    }
// دالة getItemId موقع العنصر على حسب position
    override fun getItemId(position:Int): Long {
return gridImages[position].toLong()
    }
// دالة مسئولة عن عدد العناصر التى سيتم عرضها
    override fun getCount(): Int {
        return gridImages.size
    }
}