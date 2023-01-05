package com.example.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.runtime.mutableStateOf as mutableStateOf

val fontFamily = FontFamily(
    Font(R.font.lexend_thin, FontWeight.Thin),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold)

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            DisplayImageCard()
//            BasicState()
//            StyledText()
//            Controls()
//            ListLayouts()
//            ConstraintLayouts()
//            SimpleAnimations()
            Navigation()


        }
    }
}


// styling text
@Composable
fun StyledText(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF101010))){
        Text(
            text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 50.sp
                        )
                    ){
                        append("J")
                     }
                    append("etpack ")
                     withStyle(
                         style = SpanStyle(
                           color = Color.Green,
                           fontSize = 50.sp
                    )
                    ){
                    append("C")
                     }
                    append("ompose")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

// image card
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier

){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation =5.dp
    ){
        Box(modifier = Modifier.height(200.dp) ){
            Image(painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop

            )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 300f
                )
            )){

            }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ){
            Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
        }
        }
    }
}

@Composable
fun DisplayImageCard(){
    val painter = painterResource(id = R.drawable.img)
    val description = "While image"
    val title = "White image that i selected"
    Box(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(16.dp)
    ){
        ImageCard(
            painter = painter ,
            contentDescription = description ,
            title = title )
    }
}

// state

@Composable
fun BasicState(){
    Column(Modifier.fillMaxSize()) {
        val color = remember {
            mutableStateOf( Color.Yellow)
        }
        ColorBox(

            Modifier
                .weight(1f)
                .fillMaxSize()
        ){
            color.value = it
        }
        Box(modifier = Modifier
            .background(color.value)
            .weight(1f)
            .fillMaxSize()
        )
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit

){

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextInt(),
                    Random.nextInt(),
                    Random.nextInt(),
                )
            )
        }
    )
}

// textfields, buttons & showing snackbars
@Composable
fun Controls(){
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState =scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState,
                label = {
                    Text("Enter your name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                }
            }) {
                Text("Please Great me")
            }
        }
    }
}

// lists
@Composable
fun ListLayouts(){
    LazyColumn {
        itemsIndexed(
            listOf("This", "is", "Jetpack", "Compose")
        ){index, item ->
            Text(
                text = item,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
        }
    }
}

// ConstraintLayout

@Composable
fun ConstraintLayouts() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenbox")
        val redBox = createRefFor("redbox")
        val guideline = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
    }
    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenbox"))
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("redbox"))
    }
}

// simple animations

@Composable
fun SimpleAnimations(){
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
//        tween(
//            durationMillis = 3000,
//            delayMillis =300,
//            easing = LinearOutSlowInEasing
//        )
//        spring(
//            Spring.DampingRatioHighBouncy
//        )

//          keyframes {
//              durationMillis= 5000
//              sizeState at 0 with LinearEasing
//              sizeState * 1.5f at 1000 with FastOutLinearInEasing
//              sizeState * 2f at 5000
//          }
            tween(
                durationMillis = 1000,
            )
    )
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ){
    Button(onClick = {
        sizeState += 50.dp
    }) {
        Text("Increase Size")
    }

    }


}


