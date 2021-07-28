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
    var mPaint = Paint()
    var mPrevX = 0f
    var mPrevY = 0f
    override fun onDraw(canvas: Canvas) {
       if (mBitmap != null){
           canvas.drawBitmap(mBitmap!!, 0f, 0f, mPaint)
       }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mBitmap = Bitmap.createBitmap(right - left, bottom - top, Bitmap.Config.ARGB_8888)
        if (mBitmap != null){
            mCanvas = Canvas(mBitmap!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mPaint.strokeWidth = 5f
        when (event.action){
            MotionEvent.ACTION_DOWN -> {
                mPrevX = event.x
                mPrevY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                mCanvas?.drawLine(mPrevX, mPrevY, event.x, event.y, Palette.paint)
                mPrevX = event.x
                mPrevY = event.y
                invalidate()
            }
        }

        return true
    }
}