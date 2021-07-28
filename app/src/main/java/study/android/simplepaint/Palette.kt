package study.android.simplepaint

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.util.Base64
import java.io.ByteArrayOutputStream


object Palette {
    var paint = Paint()

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setSize(size: Int) {
        paint.strokeWidth = size.toFloat()
    }

    fun encodeToBase64(image: Bitmap?): String {
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size).copy(Bitmap.Config.ARGB_8888, true)
    }

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }
}

