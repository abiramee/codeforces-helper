package com.abiramee.codeforceshelper.presentation.ui.solve_list


import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abiramee.codeforceshelper.R
import com.abiramee.codeforceshelper.presentation.theme.DarkBackgroundColor
import com.abiramee.codeforceshelper.presentation.theme.Purple200
import com.abiramee.codeforceshelper.presentation.theme.UbuntuMonoFamily
import com.abiramee.codeforceshelper.presentation.ui.solve_list.component.EmptyListComponent
import com.abiramee.codeforceshelper.presentation.ui.solve_list.component.InfoBox
import com.abiramee.codeforceshelper.presentation.ui.solve_list.component.NoInternetComponent
import com.abiramee.codeforceshelper.presentation.ui.solve_list.component.SolvedListItem

@Composable
fun SolvedListScreen(viewModel: SolvedListViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .background(DarkBackgroundColor)
            .fillMaxSize()
    ) {
        val state = viewModel.state.value

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (state.data != null) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                contentPadding = PaddingValues(10.dp, 20.dp)
            ) {
                item() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InfoBox(
                            viewModel.state.value.data?.monthlyCount ?: 0,
                            "Solved This Month",
                            modifier = Modifier
                                .weight(1f)
                                .align(CenterVertically)
                        )

                        InfoBox(
                            viewModel.state.value.data?.dailyCount ?: 0,
                            "Solved This Day",
                            modifier = Modifier
                                .weight(1f)
                                .align(CenterVertically)
                        )

                    }
                }
                state.data.let { result ->
                    items(result.acceptedList) { item ->
                            SolvedListItem(item);
                    }

                    if (result.acceptedList.isEmpty()) {
                        item {
                            Box(modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 0.dp)) {
                                EmptyListComponent()
                            }
                        }
                    }
                }
            }
        } else if (state.error.isNotEmpty()) {
            if (state.error.contains("internet")) {
                NoInternetComponent(text = state.error)
            } else {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}