package com.yoesuv.composenetworking.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yoesuv.composenetworking.R
import com.yoesuv.composenetworking.ui.route.AppRoute
import com.yoesuv.composenetworking.ui.screens.components.AppTopBar
import com.yoesuv.composenetworking.ui.theme.ComposeNetworkingTheme

@Composable
fun HomeScreen(nav: NavHostController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.app_name),
                canBack = false
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.select_menu),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    nav.navigate(AppRoute.ListPlace)
                }) {
                Text(text = stringResource(id = R.string.list_place).uppercase())
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    nav.navigate(AppRoute.Gallery)
                }) {
                Text(text = stringResource(id = R.string.gallery_place).uppercase())
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ComposeNetworkingTheme {
        HomeScreen(rememberNavController())
    }
}