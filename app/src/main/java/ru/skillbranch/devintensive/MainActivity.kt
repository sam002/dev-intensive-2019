package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: TextView
    lateinit var sendBtn: ImageView

    lateinit var benderObj:Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("S_MainActivity", "onCreate")

//        benderImage = findViewById(R.id.iv_bender)
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?:Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?:Bender.Question.NAME.name
        val answer = savedInstanceState?.getString("ANSWER") ?: ""
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()

        messageEt.text = answer

        sendBtn.setOnClickListener(this)
        messageEt.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                processAnswer()
                hideKeyboard()
                true
            } else {
                false
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("S_MainActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("S_MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("S_MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("S_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("S_MainActivity", "onStop")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send) {
            processAnswer()
        }
    }

    private fun processAnswer()
    {
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
        val (r,g,b) = color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
        messageEt.text = ""
//        if (isKeyboardOpen()) {
//            hideKeyboard()
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
        outState.putString("ANSWER", messageEt.text.toString())
        Log.d("S_MainActivity", "onSaveInstanceState${benderObj.status.name}, ${benderObj.question.name}")
    }
}
