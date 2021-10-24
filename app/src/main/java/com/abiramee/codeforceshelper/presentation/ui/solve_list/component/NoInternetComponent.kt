package com.abiramee.codeforceshelper.presentation.ui.solve_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abiramee.codeforceshelper.R
import com.abiramee.codeforceshelper.presentation.theme.UbuntuMonoFamily

@Composable
fun NoInternetComponent(text: String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.no_internet_illustration),
            contentDescription = "Empty illustration"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = UbuntuMonoFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(32.dp, 10.dp),
            textAlign = TextAlign.Center,
        )
    }
}