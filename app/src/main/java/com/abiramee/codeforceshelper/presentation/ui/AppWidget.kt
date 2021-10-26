package com.abiramee.codeforceshelper.presentation.ui

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ContentValues.TAG
import android.content.Context
import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abiramee.codeforceshelper.R
import com.abiramee.codeforceshelper.common.DataStorePreference
import com.abiramee.codeforceshelper.common.Resource
import com.abiramee.codeforceshelper.domain.model.Status
import com.abiramee.codeforceshelper.domain.use_case.CheckUserUseCase
import com.abiramee.codeforceshelper.domain.use_case.GetStatusUseCase
import com.abiramee.codeforceshelper.presentation.ui.have_user_name.HaveUserNameViewModel
import com.abiramee.codeforceshelper.presentation.ui.solve_list.SolvedListState
import com.abiramee.codeforceshelper.presentation.ui.solve_list.SolvedListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject
import android.app.PendingIntent

import android.content.Intent




/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
class AppWidget : AppWidgetProvider() {

    @Inject
    lateinit var dataStorePreference: DataStorePreference

    @Inject
    lateinit var userCase: GetStatusUseCase

    @DelicateCoroutinesApi
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            GlobalScope.launch(Dispatchers.IO) {
                val username = dataStorePreference.readString(DataStorePreference.USER_NAME);
                if (username.isNotEmpty()) {
                    userCase(dataStorePreference.readString(DataStorePreference.USER_NAME)).onEach { result ->
                        when (result) {
                            is Resource.Success<Status> -> {
                                dataStorePreference.save(
                                    DataStorePreference.MONTHLY_COUNT,
                                    result.data!!.monthlyCount
                                )
                                dataStorePreference.save(
                                    DataStorePreference.DAILY_COUNT,
                                    result.data.dailyCount
                                )
                            }
                        }

                        updateAppWidget(context, appWidgetManager, appWidgetId, 1)
                    }.launchIn(GlobalScope)
                } else {
                    updateAppWidget(context, appWidgetManager, appWidgetId, 0)
                }
            }
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private suspend fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        userAvailable: Int,
    ) {
        val views = RemoteViews(context.packageName, R.layout.app_widget)

        val intent = Intent(context, SplashActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        views.setOnClickPendingIntent(R.id.container, pendingIntent)

        if (userAvailable == 1) {
            views.setViewVisibility(R.id.linearLayout_error, View.GONE)
            views.setViewVisibility(R.id.linearLayout_success, View.VISIBLE)
            views.setTextViewText(
                R.id.text_monthly_count,
                dataStorePreference.readInt(DataStorePreference.MONTHLY_COUNT).toString()
            )
            views.setTextViewText(
                R.id.text_daily_count,
                dataStorePreference.readInt(DataStorePreference.DAILY_COUNT).toString()
            )
        } else {
            views.setViewVisibility(R.id.linearLayout_error, View.VISIBLE)
            views.setViewVisibility(R.id.linearLayout_success, View.GONE)
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}