package com.yoesuv.composenetworking.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.yoesuv.composenetworking.R
import com.yoesuv.composenetworking.ui.screens.components.AppTopBar
import com.yoesuv.composenetworking.ui.theme.ComposeNetworkingTheme

@Composable
fun GalleryPlaceScreen(nav: NavHostController, viewModel: GalleryViewModel = viewModel()) {

    val loading = viewModel.loading.value
    val galleries by viewModel.galleries.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadGallery()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.gallery_place),
                canBack = true,
                navigateUp = {
                    nav.navigateUp()
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (loading) {
            LoadingScreen()
        } else {
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                ) {
                    items(galleries) { gallery ->
                        ItemGallery(imageUrl = gallery.thumbnail ?: "")
                    }
                }
            }
        }
    }
}

@Composable
fun ItemGallery(imageUrl: String) {
    AsyncImage(model = imageUrl, contentDescription = imageUrl, contentScale = ContentScale.Crop)
}

@Preview(showBackground = true)
@Composable
fun GalleryPlaceScreenPreview() {
    ComposeNetworkingTheme {
        GalleryPlaceScreen(rememberNavController())
    }
}