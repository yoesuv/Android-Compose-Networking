package com.yoesuv.composenetworking.ui.route

import kotlinx.serialization.Serializable

sealed class AppRoute {
    @Serializable data object Home: AppRoute()
    @Serializable data object ListPlace: AppRoute()
    @Serializable data object Gallery: AppRoute()
}