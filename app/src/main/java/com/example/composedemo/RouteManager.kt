package com.example.composedemo

import com.example.composedemo.state.StateActivity

object RouteManager {

    fun getRoutes() = listOf(
        Route(
            "状态管理",
            StateActivity::class.java
        )
    )

}

data class Route (
    val label: String,
    val dest: Class<*>
)