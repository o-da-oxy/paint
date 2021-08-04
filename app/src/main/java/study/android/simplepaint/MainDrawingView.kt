package study.android.simplepaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MainDrawingView (context: Context?, attrs: AttributeSet?) : View(context, attrs){
    var mBitmap: Bitmap? = null
    var mCanvas: Canvas? = null
    var mPaint = Paint() //можно создать один раз на уровне класса
    var mPrevX = 0f
    var mPrevY = 0f

    override fun onDraw(canvas: Canvas) { //вызывается ситемой
        if (mBitmap != null){
            canvas.drawBitmap(mBitmap!!, 0f, 0f, mPaint) //отображет рисунок
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mBitmap == null) {
            //создаем чистый
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }
        if (mBitmap != null) {
            //если уже что-то нарисовано
            mCanvas = Canvas(mBitmap!!)
        }

    }



    override fun onTouchEvent(event: MotionEvent): Boolean {
        mPaint.strokeWidth = 5f
        when (event.action){
            MotionEvent.ACTION_DOWN -> { //содержит инфу о касании (координаты)
                //начинаем новую линию
                mPrevX = event.x
                mPrevY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                //соединяем точки
                mCanvas?.drawLine(mPrevX, mPrevY, event.x, event.y, Palette.paint)
                //объединяем коориднаты - новые становятся предыдущими
                mPrevX = event.x
                mPrevY = event.y
                invalidate() //прорисовка, обновление View
            }
        }
        return true
    }



}