package study.android.simplepaint

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.palette.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linkPalette()
    }

    //Функции работы с палитрой
    fun linkPalette() {
        whitecolor.setOnClickListener {onColorChange(it)}
        blackcolor.setOnClickListener {onColorChange(it)}
        redcolor.setOnClickListener {onColorChange(it)}
        greencolor.setOnClickListener {onColorChange(it)}
        bluecolor.setOnClickListener {onColorChange(it)}
        brush1.setOnClickListener {onSizeChange(it)}
    }

    //Обработка нажатий кнопок палитры
    fun onColorChange(view: View) {
        val color = ((view as ImageView).drawable as ColorDrawable).color
        Palette.setColor(color)
    }

    fun onSizeChange(view: View) {
        val size = (view as ImageView).drawable.intrinsicWidth
        Palette.setSize(size)
    }

    override fun onPause(){
        super.onPause()
        getPreferences(Context.MODE_PRIVATE).edit().putString("pic",
           Palette.encodeToBase64(mainDrawingView.mBitmap)).apply()
    }

    override fun onResume(){
        super.onResume()
        val savedBmp = getPreferences(Context.MODE_PRIVATE).getString("pic2\\", null)
        if (savedBmp != null)
            mainDrawingView.mBitmap = Palette.decodeBase64(savedBmp)
    }
}