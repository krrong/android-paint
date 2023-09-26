package woowacourse.paint.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val path = Path()
    private val paint = Paint()
    private var ovalSize = 0

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> { addOvalToPath(pointX, pointY) }
            MotionEvent.ACTION_MOVE -> { addOvalToPath(pointX, pointY) }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun addOvalToPath(x: Float, y: Float) {
        path.addOval(
            x - (ovalSize / 2),
            y - (ovalSize / 2),
            x + (ovalSize / 2),
            y + (ovalSize / 2),
            Path.Direction.CW,
        )
    }

    fun changeThickness(new: Float) {
        ovalSize = (new * NORMALIZATION).toInt()
    }

    fun changeColor(new: Int) {
        paint.color = new
    }

    companion object {
        const val NORMALIZATION = 100
    }
}
