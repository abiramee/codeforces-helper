package com.abiramee.codeforceshelper.presentation.ui.have_user_name

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.scaleMatrix
import androidx.hilt.navigation.compose.hiltViewModel
import com.abiramee.codeforceshelper.R
import com.abiramee.codeforceshelper.presentation.theme.DarkBackgroundColor
import com.abiramee.codeforceshelper.presentation.theme.UbuntuMonoFamily
import com.abiramee.codeforceshelper.presentation.ui.solve_list.component.NoInternetComponent
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalComposeUiApi
@Composable
fun HaveUserNameScreen(
    viewModel: HaveUserNameViewModel = hiltViewModel(),
) {

    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state.value;

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.error.isNotEmpty() && state.error.contains("internet")) {
            NoInternetComponent(text = state.error)
        } else if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        } else {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackgroundColor),
            ) {
                // Create references for the composables to constrain
                val (title, editText, floatingButton, loadingBar) = createRefs()

                Text(
                    text = "Codeforces Helper",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = UbuntuMonoFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(parent.top, margin = 250.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)

                        }
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                OutlinedTextField(
                    value = text, onValueChange = { text = it.lowercase(Locale.getDefault()) },
                    modifier = Modifier
                        .constrainAs(editText) {
                            top.linkTo(title.bottom, margin = 32.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(DarkBackgroundColor),
                    label = {
                        Text(
                            text = "Enter your codeforces's username",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontFamily = UbuntuMonoFamily,
                            textAlign = TextAlign.Start,
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = UbuntuMonoFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            viewModel.getStatus(text.trim())
                        }
                    ),
                )
                if (!state.isLoading) {
                    IconButton(
                        onClick = {
                            viewModel.getStatus(text.trim())
                        },
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF504FC0),
                                        Color(0xFF6C92F4)
                                    )
                                ),
                                shape = RoundedCornerShape(100.dp)
                            )
                            .constrainAs(floatingButton) {
                                end.linkTo(parent.end, margin = 32.dp)
                                bottom.linkTo(parent.bottom, margin = 32.dp)
                            },

                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_arrow_forward_24),
                            contentDescription = "Add icon",
                            tint = Color.White
                        )
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.constrainAs(loadingBar) {
                            bottom.linkTo(parent.bottom, margin = 32.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                    )
                }
            }
        }
    }
}