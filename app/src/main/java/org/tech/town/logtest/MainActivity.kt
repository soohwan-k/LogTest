package org.tech.town.logtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jakewharton.threetenabp.AndroidThreeTen
import org.tech.town.logtest.databinding.ActivityMainBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //년월 변수
    lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //binding 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        AndroidThreeTen.init(this)

        //현재 날짜
        selectedDate  = LocalDate.now()

        //화면 설정
        setMonthView()

        //이전달 버튼 이벤트
        binding.preBtn.setOnClickListener {
            //현재 월 -1 변수에 담기
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }

        //다음달 버튼 이벤트
        binding.nextBtn.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }
    }

    //날짜 화면에 보여주기
    private fun setMonthView() {
        //년월 텍스트뷰 셋팅
        binding.monthYearText.text = monthYearFromDate(selectedDate)
    }

    //날짜 타입 설정
    private fun monthYearFromDate(date: LocalDate): String{

        var formatter = DateTimeFormatter.ofPattern("MM월 yyyy")

        // 받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }
}