package com.yoesuv.composenetworking.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.yoesuv.composenetworking.R
import com.yoesuv.composenetworking.models.PlaceModel
import com.yoesuv.composenetworking.ui.screens.components.AppTopBar
import com.yoesuv.composenetworking.ui.theme.ComposeNetworkingTheme

@Composable
fun ListPlaceScreen(nav: NavHostController) {
    val viewModel: ListPlaceViewModel = viewModel()
    val loading = viewModel.loading.value
    val places by viewModel.places.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadListPlace()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.list_place),
                canBack = true,
                navigateUp = {
                    nav.navigateUp()
                }
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        if (loading) {
            LoadingScreen()
        } else {
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyColumn {
                    items(places) { place ->
                        ItemPlace(placeModel = place)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemPlace(placeModel: PlaceModel) {
    Row {
        Box(modifier = Modifier.size(60.dp, 60.dp)) {
            AsyncImage(
                model = placeModel.thumbnail,
                contentDescription = placeModel.name,
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = placeModel.name ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = placeModel.location ?: "",
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPlaceScreenPreview() {
    ComposeNetworkingTheme {
        ListPlaceScreen(rememberNavController())
    }
}