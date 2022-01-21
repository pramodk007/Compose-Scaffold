package com.example.compose_scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_scaffold.ui.theme.ComposeScaffoldTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldExampleTwo()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun ScaffoldDemo() {
    val materialBlue700 = Color(0xFF1976D2)
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TopAppBar") },
                backgroundColor = materialBlue700
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Text(text = "X")
            }
        },
        drawerContent = { Text(text = "drawerContent") },
        content = { Text(text = "BodyContent") },
        bottomBar = {
            BottomAppBar(backgroundColor = materialBlue700) {
                Text(text = "BottomAppBar")
            }
        }
    )
}

@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Scaffold", color = Color.White)
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable(onClick = onMenuClicked),
                tint = Color.White
            )
        },
        backgroundColor = Color(0xFF0F9D58)
    )
}

@Composable
fun BottomBar() {
    BottomAppBar(backgroundColor = Color(0xFF0F9D58)) {
        Text(text = "Bottom App Bar", color = Color.White)
    }
}

@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Body Content", color = Color(0xFF0F9D58))
    }
}

@Composable
fun Drawer() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        repeat(5) { item ->
            Text(text = "Item Number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}
@Preview
@Composable
fun ScaffoldExampleTwo() {
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                onMenuClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        bottomBar = { BottomBar() },
        content = {
            Body()
        },
        drawerContent = {
            Drawer()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        when (scaffoldState.snackbarHostState.showSnackbar(
                            message = "Snack bar",
                            actionLabel = "Dismiss"
                        )) {
                            SnackbarResult.Dismissed -> {
                                // do something when
                                // snack bar is dismissed
                            }
                            SnackbarResult.ActionPerformed -> {
                                // when it appears
                            }
                        }
                    }
                }
            ) {
                Text(text = "X")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeScaffoldTheme {
        Greeting("Android")
    }
}