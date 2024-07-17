package eu.practice.texttospeach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import eu.practice.texttospeach.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts : TextToSpeech?=null
    private var binding : ActivityMainBinding? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tts= TextToSpeech(this,this)

        binding?.btnSpeak?.setOnClickListener { view ->
            if (binding?.entertext?.text!!.isEmpty()){
                Toast.makeText(this,"Please Enter the Text",Toast.LENGTH_SHORT).show()
            }else{
                // ToDo Speak To Text Please
            speakOut(binding?.entertext?.text.toString())
            }


        }


    }

    override fun onInit(status: Int) {
       // TODO("Not yet implemented")
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language is not Supported ! ")
            }else{
                Log.e("TTS","Initialization iS Failed")
            }
        }
    }

    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts !=null){
            tts?.stop()
            tts?.shutdown()
        }
        binding = null
    }

}