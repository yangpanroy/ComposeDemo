package com.example.composedemo

import com.example.composedemo.layout.ConstraintLayoutActivity
import com.example.composedemo.layout.LayoutActivity
import com.example.composedemo.state.StateActivity

object RouteManager {

    fun getRoutes() = listOf(
        Route(
            "状态管理",
            StateActivity::class.java
        ),
        Route(
            "基础布局",
            LayoutActivity::class.java
        ),
        Route(
            "约束布局",
            ConstraintLayoutActivity::class.java
        )
    )

}

data class Route (
    val label: String,
    val dest: Class<*>
)