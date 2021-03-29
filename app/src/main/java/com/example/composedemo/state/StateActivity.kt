package com.example.composedemo.state

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class StateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(StateViewModel::class.java)
        setContent {
            Page(viewModel)
        }
    }

    @Preview
    @Composable
    fun Page(viewModel: StateViewModel) {
        // 普通状态
        val (count, setCount) = remember { mutableStateOf(0) }
        // 计算状态
        val computedCount = remember(count) { count * 2 }
        // 重建可回写状态
        val (savedCount, setSavedCount) = rememberSaveable { mutableStateOf(0) }
        // 从 ViewModel 绑定的状态
        val viewModelCount = viewModel.count.observeAsState(0)


        LazyColumn(
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                StateItem(
                    label = "普通状态: ",
                    value = count,
                    onClick = { setCount(count + 1) }
                )
            }

            item {
                StateItem(
                    label = "计算状态: ",
                    value = computedCount,
                    onClick = { setCount(count + 1) }
                )
            }

            item {
                StateItem(
                    label = "重建可回写状态: ",
                    value = savedCount,
                    onClick = { setSavedCount(savedCount + 1) }
                )
            }

            item {
                StateItem(
                    label = "从 ViewModel 绑定的状态: ",
                    value = viewModelCount.value,
                    onClick = { viewModel.increase() }
                )
            }
        }

    }

    @Composable
    fun StateItem(label: String, value: Int, buttonText: String = "增加", onClick: () -> Unit) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(label)
                Text("$value")
                Button(onClick = { onClick() }) {
                    Text(buttonText)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}