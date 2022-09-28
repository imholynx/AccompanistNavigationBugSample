package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navController = rememberNavController(bottomSheetNavigator)

                ModalBottomSheetLayout(bottomSheetNavigator) {
                    NavHost(navController, "home") {
                        composable("home") { Home(navController) }
                        bottomSheet("bottom_sheet") { BottomSheet() }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Home(navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyColumn {
        item { TextField(value = "", onValueChange = {}) }
        repeat(99) {
            item {
                Text(text = it.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            keyboardController?.hide()
                            navController.navigate("bottom_sheet")
                        })
            }
        }
    }
}

@Composable
private fun BottomSheet() {
    LazyColumn {
        repeat(9) {
            item { Text(text = it.toString()) }
        }
    }
}