package com.example.composedemo.layout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composedemo.R

class ConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Page() }
    }

    @Preview
    @Composable
    private fun Page() {
        val scrollState = ScrollState(0)

        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            ItemCard()
            Spacer(modifier = Modifier.height(16.dp))
            LoginCard()
            Spacer(modifier = Modifier.height(16.dp))
            GuideLineCard()
            Spacer(modifier = Modifier.height(16.dp))
            ChainCard()
        }
    }

    @Composable
    fun ItemCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color(0xFFFFFFFF)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                val (avatar, title, subTitle, image) = createRefs()

                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(avatar) {
                            start.linkTo(parent.start, 16.dp)
                            top.linkTo(parent.top, 16.dp)
                        }
                        .height(80.dp)
                        .width(80.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                )
                Text(
                    text = "用户名称",
                    modifier = Modifier
                        .constrainAs(title) {
                            start.linkTo(avatar.end, 16.dp)
                            top.linkTo(avatar.top)
                        },
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF333333)
                )
                Text(
                    text = "用户备注可能是一段很长很长的话，需要最多显示两行，然后进行折行，折行后如果继续超出，则需要进行省略处理",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    color = Color(0xFF999999),
                    modifier = Modifier
                        .constrainAs(subTitle) {
                            start.linkTo(title.start)
                            top.linkTo(title.bottom, 8.dp)
                            end.linkTo(parent.end, 16.dp)
                            width = Dimension.fillToConstraints
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.scene),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(avatar.bottom, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                            width = Dimension.fillToConstraints
                        }
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }

    // 利用Barrier进行布局
    @Composable
    fun LoginCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color(0xFFFFFFFF)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                val (labelAccount, labelPassword, inputAccount, inputPassword) = createRefs()
                val barrier = createEndBarrier(labelAccount, labelPassword)
                val (account, setAccount) = remember { mutableStateOf("") }
                val (password, setPassword) = remember { mutableStateOf("") }

                Text(
                    text = "账户名称：",
                    modifier = Modifier.constrainAs(labelAccount) {
                        top.linkTo(inputAccount.top)
                        bottom.linkTo(inputAccount.bottom)
                        start.linkTo(parent.start, 16.dp)
                    },
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "密码：",
                    modifier = Modifier.constrainAs(labelPassword) {
                        top.linkTo(inputPassword.top)
                        bottom.linkTo(inputPassword.bottom)
                        start.linkTo(parent.start, 16.dp)
                    },
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = account,
                    onValueChange = { setAccount(it) },
                    placeholder = { Text("请输入账户名称") },
                    modifier = Modifier.constrainAs(inputAccount) {
                        start.linkTo(barrier, 8.dp)
                        top.linkTo(parent.top, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        width = Dimension.fillToConstraints
                    }
                )
                TextField(
                    value = password,
                    onValueChange = { setPassword(it) },
                    placeholder = { Text("请输入密码") },
                    modifier = Modifier.constrainAs(inputPassword) {
                        start.linkTo(barrier, 8.dp)
                        top.linkTo(inputAccount.bottom, 8.dp)
                        end.linkTo(parent.end, 16.dp)
                        width = Dimension.fillToConstraints
                    }
                )
            }
        }
    }

    // 利用GuidLine进行布局（使用constraintSet定义约束）
    @Composable
    fun GuideLineCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color(0xFFFFFFFF)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                constraintSet = ConstraintSet {
                    val box1 = createRefFor("box1")
                    val box2 = createRefFor("box2")
                    val guideline = createGuidelineFromStart(0.2f)

                    constrain(box1) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(guideline)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }

                    constrain(box2) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(guideline)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }

                }
            ) {
                Box(
                    modifier = Modifier
                        .layoutId("box1")
                        .background(Color(0xFFFF0000))
                )
                Box(
                    modifier = Modifier
                        .layoutId("box2")
                        .background(Color(0xFF0000FF))
                )
            }
        }
    }

    // 利用Chain进行布局
    @Composable
    fun ChainCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color(0xFFFFFFFF)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                constraintSet = ConstraintSet {
                    val box1 = createRefFor("box1")
                    val box2 = createRefFor("box2")
                    val box3 = createRefFor("box3")
                    createHorizontalChain(
                        elements = arrayOf(box1, box2, box3),
                        chainStyle = ChainStyle.SpreadInside
                    )

                }
            ) {
                Box(
                    modifier = Modifier
                        .layoutId("box1")
                        .size(70.dp)
                        .background(Color(0xFFFF0000))
                )
                Box(
                    modifier = Modifier
                        .layoutId("box2")
                        .size(70.dp)
                        .background(Color(0xFF0000FF))
                )
                Box(
                    modifier = Modifier
                        .layoutId("box3")
                        .size(70.dp)
                        .background(Color(0xFF00FF00))
                )
            }
        }
    }

}