package org.tech.town.logtest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate

class CalendarAdapter(private val dayList: ArrayList<LocalDate?>):
    RecyclerView.Adapter<CalendarAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dayText: TextView = itemView.findViewById(R.id.dayText)
        val mlText: TextView = itemView.findViewById(R.id.mlText)
    }

    //화면 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)

        return ItemViewHolder(view)
    }

    //데이터 설정
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var day = dayList[holder.adapterPosition]

        if(day == null){
            holder.dayText.text=""
            holder.mlText.text=""
        }else{
            holder.dayText.text = day.dayOfMonth.toString()
            holder.mlText.text = "300ml"
            if(day == CalendarUtil.selectedDate){
                holder.itemView.setBackgroundColor(Color.LTGRAY)
            }
        }
        //토, 일 색상 지정
        if((position+1)%7==0){
            holder.dayText.setTextColor(Color.BLUE)
        }
        else if(position==0 || position%7==0){
            holder.dayText.setTextColor(Color.RED)
        }

        //날짜 클릭 이벤트
        holder.itemView.setOnClickListener{

            var iYear = day?.year
            var iMonth = day?.monthValue
            var iDay = day?.dayOfMonth

            var yearMonDay = "$iYear 년 $iMonth 월 $iDay 일"

            Toast.makeText(holder.itemView.context, yearMonDay, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}