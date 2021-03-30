package com.example.composedemo.layout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Page() }
    }

    @Preview
    @Composable
    private fun Page() {
        val rowScrollState = ScrollState(0)
        val columnScrollState = ScrollState(0)

        Column {
            // 垂直布局
            Column(modifier = Modifier.height(50.dp)) {
                Text("垂直布局1")
                Text("垂直布局2")
            }
            // 水平布局
            Row(modifier = Modifier.height(50.dp)) {
                Text("水平布局1")
                Text("水平布局2")
            }
            // 盒子布局
            Box(modifier = Modifier.height(50.dp)) {
                Text("盒子布局1")
                Text("盒子布局2")
            }
            // 可滚动水平布局
            Row(
                modifier = Modifier
                    .horizontalScroll(rowScrollState)
                    .height(50.dp)
            ) {
                (1..20).map { Text("第 $it 条") }
            }
            // 回收复用机制的水平列表
            LazyRow(
                modifier = Modifier.height(50.dp)
            ) {
                items(20) {
                    Text("第 $it 条")
                }
            }
            Row(modifier = Modifier.fillMaxHeight()) {
                // 可滚动的垂直布局
                Column(
                    modifier = Modifier
                        .verticalScroll(columnScrollState)
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)

                ) {
                    (1..100).map { Text("第 $it 条") }
                }
                // 回收复用机制的垂直布局
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                ) {
                    items(100) {
                        Text("第 $it 条")
                    }
                }
            }

        }
    }
}