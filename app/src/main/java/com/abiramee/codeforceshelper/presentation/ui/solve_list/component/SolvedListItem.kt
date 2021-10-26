package com.abiramee.codeforceshelper.presentation.ui.solve_list.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abiramee.codeforceshelper.domain.model.AcceptedItem
import com.abiramee.codeforceshelper.presentation.theme.CodeforcesHelperTheme
import com.abiramee.codeforceshelper.presentation.theme.DarkBackgroundColor
import com.abiramee.codeforceshelper.presentation.theme.UbuntuMonoFamily
import com.abiramee.codeforceshelper.presentation.ui.solve_list.SolvedListScreen

@Composable
fun SolvedListItem(acceptedItem: AcceptedItem) {
    Box(modifier = Modifier.padding(10.dp)) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF3A3B3C))
                .animateContentSize(),
        ) {
            Row( modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = acceptedItem.name,
                    fontFamily = UbuntuMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(3f)
                        .padding(10.dp, 0.dp),
                    color = Color.White,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = "Accepted",
                    fontFamily = UbuntuMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF00AA00)
                )
            }

            Row( modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = acceptedItem.tag,
                    fontFamily = UbuntuMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .weight(3f)
                        .padding(10.dp, 0.dp),
                    color = Color.White,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = acceptedItem.rating,
                    fontFamily = UbuntuMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodeforcesHelperTheme {
        // A surface container using the 'background' color from the theme
        SolvedListItem(
            acceptedItem = AcceptedItem(
                "A.Array Elimination",
                "tags: bitmasks, math, number theory",
                "*1300"
            )
        )
    }
}
