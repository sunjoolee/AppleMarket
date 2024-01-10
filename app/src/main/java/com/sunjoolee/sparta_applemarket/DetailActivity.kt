package com.sunjoolee.sparta_applemarket

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sunjoolee.sparta_applemarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val TAG = "DetailActivity"
    companion object{
        const val EXTRA_POST = "extra_post"
        fun newIntent(context:Context,post:Post): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_POST, post)
            }
    }

    private lateinit var binding:ActivityDetailBinding
    private val post by lazy{ intent.getParcelableExtra<Post>(EXTRA_POST) ?: null}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //상세 화면 그리기
        initUI()
        //뒤로가기 버튼
        initBackButton()
    }

    private fun initUI(){
        if(post == null){
            Log.d(TAG, "initUI) post is null")
            return
        }
        binding.apply {
            with(post!!){
                detailIvImage.setImageResource(imageID)
                detailTvWriter.text = writer
                detailTvLoction.text = location
                detailTvTitle.text = title
                detailTvBody.text = body
                detailTvPrice.text = price.toString() + "원"
            }
        }
    }
    private fun initBackButton(){
        binding.datailIvBack.setOnClickListener {
            Log.d(TAG, "back button clicked")
            finish()
        }
    }
}