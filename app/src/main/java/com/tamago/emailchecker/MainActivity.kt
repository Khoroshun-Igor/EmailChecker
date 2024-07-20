package com.tamago.emailchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tamago.emailchecker.ui.theme.EmailCheckerTheme
import com.tamago.features.EmailCheckerMainScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailCheckerTheme {
                EmailCheckerMainScreen()
            }
        }
    }
}