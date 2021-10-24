package com.abiramee.codeforceshelper.presentation.ui.solve_list.component

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abiramee.codeforceshelper.presentation.theme.Purple200
import com.abiramee.codeforceshelper.presentation.theme.UbuntuMonoFamily

@Composable
fun InfoBox(count: Int, text: String, modifier: Modifier) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = count.toString(),
                color = Purple200,
                fontSize = 72.sp,
                fontFamily = UbuntuMonoFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = UbuntuMonoFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}