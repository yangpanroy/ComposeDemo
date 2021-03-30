package com.example.composedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val routes = RouteManager.getRoutes()
        setContent {
            Page(routes)
        }
    }

    private fun goTo(dest: Class<*>) {
        startActivity(Intent(this, dest))
    }

    @Composable
    fun Page(routes: List<Route>) {
        LazyColumn {
            items(routes.size) {
                Spacer(Modifier.size(8.dp))
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .clickable { goTo(routes[it].dest) }
                        .fillMaxWidth()
                        .background(Color(0xFFEBEBEB)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(routes[it].label)
                }
                Spacer(Modifier.size(8.dp))
            }
        }
    }

}