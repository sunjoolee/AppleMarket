package com.sunjoolee.sparta_applemarket

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunjoolee.sparta_applemarket.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataSet:MutableList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSet = getDummyData()
        initRecyclerView(dataSet)
    }


    private fun initRecyclerView(dataSet: MutableList<Post>) {
        binding.mainRecyclerView.apply {
            //set LayoutManager
            layoutManager = LinearLayoutManager(context)

            //set adapter
            adapter = MyAdapter(dataSet).apply {
                //set adapter itemClick
                itemClick = object : ItemClick {
                    override fun onClick(view: View, position: Int) {
                        Log.d(TAG, "onClick) position: $position, title: ${dataSet[position].title}")
                        startActivity(DetailActivity.newIntent(context,dataSet[position]))
                    }
                    override fun onLongClick(view: View, position: Int) {
                        Log.d(TAG, "onLongClick) position: $position")
                        showDeleteDialog(position)
                    }
                }
            }
            //set divider item decoration
            addItemDecoration(
                DividerItemDecoration(context, LinearLayout.VERTICAL)
            )
        }
    }
    private fun showDeleteDialog(position:Int){
        val builder = AlertDialog.Builder(this).apply {
            setTitle(R.string.delete_dialog_title)
            setMessage(resources.getString(R.string.delete_dialog_msg,dataSet[position].title))
            setIcon(R.drawable.icon_dialog)
        }
        val listener = DialogInterface.OnClickListener { _, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE -> deleteRecyclerViewItem(position)
                DialogInterface.BUTTON_NEGATIVE -> {} //아무 동작 X
            }
        }
        builder.setPositiveButton(R.string.dialog_positive, listener)
        builder.setNegativeButton(R.string.dialog_negative, listener)
        builder.show()
    }
    private fun deleteRecyclerViewItem(position:Int){
        //선택한 항목 삭제
        dataSet.removeAt(position)
        //항목 삭제 observer에 알리기
        binding.mainRecyclerView.adapter?.notifyItemRemoved(position)
    }
    override fun onBackPressed() {
        Log.d(TAG, "back button pressed")
        showExitDialog()
    }
    private fun showExitDialog(){
        val builder = AlertDialog.Builder(this).apply {
            setTitle(R.string.end_dialog_title)
            setMessage(R.string.end_dialog_msg)
            setIcon(R.drawable.icon_dialog)
        }
        val listener = DialogInterface.OnClickListener { _, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE ->  exit()
                DialogInterface.BUTTON_NEGATIVE -> {} //아무 동작 X
            }
        }
        builder.setPositiveButton(R.string.dialog_positive, listener)
        builder.setNegativeButton(R.string.dialog_negative, listener)
        builder.show()
    }
    private fun exit(){
        super.onBackPressed() //앱 종료
    }

    private fun getDummyData(): MutableList<Post> {
        var dummyDataSet = mutableListOf<Post>(
            Post(
                1,
                R.drawable.sample1,
                "산진 한달된 선풍기 팝니다",
                "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                "대현동",
                1000,
                "서울 서대문구 창천동",
                13,
                25
            ),
            Post(
                2,
                R.drawable.sample2,
                "김치냉장고",
                "이사로 인해 내놔요",
                "안마담",
                20000,
                "인천 계양구 귤현동",
                8,
                28
            ),
            Post(
                3,
                R.drawable.sample3,
                "샤넬 카드지갑",
                "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                "코코유",
                10000,
                "수성구 범어동",
                23,
                5
            ),
            Post(
                4,
                R.drawable.sample4,
                "금고",
                "금고\n떼서 가져가야 함\n대우월드마크센텀\n미국 이주 관계로 싸게 팝니다",
                "Nicole",
                10000,
                "해운대구 우제2동",
                14,
                17
            ),
            Post(
                5,
                R.drawable.sample5,
                "갤럭시Z플립3 팝니다",
                "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한 장 챙겨 드립니다\n화면에 살짝 스크래치난 거 말고 크게 이상은 없습니다!",
                "절명",
                150000,
                "연제구 연산제8동",
                22,
                9
            ),
            Post(
                6,
                R.drawable.sample6,
                "프라다 복조리백",
                "까임 오염없고 상태 깨끗합니다\n정품 여부 모름",
                "미니멀하게",
                50000,
                "수원시 영통구 원천동",
                25,
                16
            ),
            Post(
                7,
                R.drawable.sample7,
                "울산 동해오션뷰 60 평 복층 펜트하우스 1 일 숙박권 펜션 힐링 숙소 별장",
                "울산 동해바다뷰 60 평 복층 펜트하우스 1 일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10, 000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50, 000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스 버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈, 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방 3개, 화장실 3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
                "굿리치",
                150000,
                "남구 옥동",
                142,
                54
            ),
            Post(
                8,
                R.drawable.sample8,
                "샤넬 탑핸들 가방",
                "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백!\n\n색상 : 블랙\n사이즈 : 25.5 cm * 17.5 cm * 8 cm\n구성 : 본품더스트\n\n급하게 돈이 필요해서 팝니다 ㅠㅠ",
                "난쉽",
                180000,
                "동래구 온천제2동",
                31,
                7
            ),
            Post(
                9,
                R.drawable.sample9,
                "4행정 엔진분무기 판매합니다",
                "3년 전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",
                "알뜰한",
                30000,
                "원주시 명륜2동",
                7,
                28
            ),
            Post(
                10,
                R.drawable.sample10,
                "셀린느 버킷 가방",
                "22년 신세계 대전 구매입니당\n셀린느 버킷백\n구매해서 몇 번 사용했어요\n까짐 스크래치 없습니다.\n타지역에서 보내는 거라 택배로 진행합니당!",
                "똑태현",
                190000,
                "중구 동화동",
                40,
                6
            )
        )

        return dummyDataSet
    }
}