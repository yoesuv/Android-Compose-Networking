package com.yoesuv.composenetworking.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yoesuv.composenetworking.ui.theme.ComposeNetworkingTheme

@Composable
fun ListPlaceScreen() {
    val viewModel: ListPlaceViewModel = viewModel()
    val loading = viewModel.loading.value
    val places by viewModel.places.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadListPlace()
    }

    Scaffold(modifier = Modifier.fillMaxWidth()) { innerPadding ->
        if (loading) {
            LoadingScreen()
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "List Place Size ${places.size}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ListPlaceScreenPreview() {
    ComposeNetworkingTheme {
        ListPlaceScreen()
    }
}