package com.abiramee.codeforceshelper.presentation.ui

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abiramee.codeforceshelper.presentation.theme.CodeforcesHelperTheme
import com.abiramee.codeforceshelper.presentation.theme.DarkBackgroundColor
import com.abiramee.codeforceshelper.presentation.ui.solve_list.SolvedListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CodeforcesHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = DarkBackgroundColor) {
                    SolvedListScreen()
                }
            }
        }
    }

    override fun onStop() {
        val intent = Intent(this, AppWidget::class.java)
        intent.action = "android.appwidget.action.APPWIDGET_UPDATE";
        val ids= AppWidgetManager.getInstance(application).getAppWidgetIds(
            ComponentName(application, AppWidget::class.java)
        );
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(intent)
        super.onStop()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodeforcesHelperTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = DarkBackgroundColor) {
            SolvedListScreen()
        }
    }
}

