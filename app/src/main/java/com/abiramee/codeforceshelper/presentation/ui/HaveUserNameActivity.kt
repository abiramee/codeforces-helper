package com.abiramee.codeforceshelper.presentation.ui

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel

import com.abiramee.codeforceshelper.common.DataStorePreference
import com.abiramee.codeforceshelper.presentation.theme.CodeforcesHelperTheme
import com.abiramee.codeforceshelper.presentation.theme.DarkBackgroundColor
import com.abiramee.codeforceshelper.presentation.ui.have_user_name.HaveUserNameScreen
import com.abiramee.codeforceshelper.presentation.ui.have_user_name.HaveUserNameViewModel
import com.abiramee.codeforceshelper.presentation.ui.solve_list.SolvedListScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.prefs.Preferences
import javax.inject.Inject

@AndroidEntryPoint
class HaveUserNameActivity () : AppCompatActivity() {

    private val haveUserNameViewModel: HaveUserNameViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CodeforcesHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = DarkBackgroundColor) {
                    HaveUserNameScreen()
                }
            }
        }

        haveUserNameViewModel.checkUserState.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let { result ->
                if (result == 1) {
                    val intent = Intent(this, AppWidget::class.java)
                    intent.action = "android.appwidget.action.APPWIDGET_UPDATE";
                    val ids= AppWidgetManager.getInstance(application).getAppWidgetIds(
                        ComponentName(application, AppWidget::class.java));
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                    sendBroadcast(intent)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish();
                } else if (result == 2) {
                    Toast.makeText(this, "The username isn't Correct", Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}