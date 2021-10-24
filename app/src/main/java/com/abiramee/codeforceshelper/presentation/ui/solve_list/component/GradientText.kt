package com.abiramee.codeforceshelper.presentation.ui.solve_list.component

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.abiramee.codeforceshelper.presentation.theme.Purple200

@Composable
fun GradientText(text: String, modifier: Modifier) {

    val paint = Paint().asFrameworkPaint()

    val gradientShader: Shader = LinearGradientShader(
        from = Offset(0f, 0f),
        to = Offset(64f, 0f),
        listOf(Purple200, Color(0xFF6C92F4))
    )

    Canvas(modifier.fillMaxSize()) {
        paint.apply {
            isAntiAlias = true
            textSize = 64f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            style = android.graphics.Paint.Style.FILL
            color = android.graphics.Color.parseColor("#6C92F4")
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
            maskFilter = BlurMaskFilter(30f, BlurMaskFilter.Blur.NORMAL)
        }
        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.nativeCanvas.translate(2f, 5f)
            canvas.nativeCanvas.drawText(text, 0f, 64f, paint)
            canvas.restore()
            paint.shader = gradientShader
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            paint.maskFilter = null
            canvas.nativeCanvas.drawText(text, 0f, 64f, paint)
            canvas.nativeCanvas.translate(2f, 5f)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
            paint.maskFilter = BlurMaskFilter(30f, BlurMaskFilter.Blur.NORMAL)
            canvas.nativeCanvas.drawText(text, 0f, 400f, paint)
        }
        paint.reset()
    }
}
