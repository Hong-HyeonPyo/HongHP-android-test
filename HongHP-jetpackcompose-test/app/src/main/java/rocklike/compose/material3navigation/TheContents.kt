package rocklike.android.material3navigation

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
}
@Composable
fun FavouriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Favourite Screen",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
}
@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notification Screen2222",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatScreen() {
    @Composable
    fun funData() {
// this variable use to handle list state
        val notesList = remember {
            mutableStateListOf<String>()
        }
// this variable use to handle edit text input value
        val inputvalue = remember { mutableStateOf(TextFieldValue()) }
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(Dp(60f))
            ) {
                Text(text = "맨 앞")
//                TextField(
//                    value = inputvalue.value,
//                    onValueChange = {
//                        inputvalue.value = it
//                    },
//                    modifier = Modifier.weight(0.8f),
//                    placeholder = { Text(text = "Enter your note") },
//                    keyboardOptions = KeyboardOptions(
//                        capitalization = KeyboardCapitalization.None,
//                        autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
//                    ),
//                    textStyle = TextStyle(
//                        color = Color.Black, fontSize = TextUnit.Unspecified,
//                        fontFamily = FontFamily.SansSerif
//                    ),
//                    maxLines = 1,
//                    singleLine = true
//                )
                Button(
                    onClick = {
                        notesList.add(inputvalue.value.text)
                    },
                    modifier = Modifier
                        .weight(0.2f)
                        .fillMaxHeight()
                ) {
                    Text(text = "ADD")
                }
            }
            Spacer(modifier = Modifier.height(Dp(1f)))

            Surface(modifier = Modifier.padding(all = Dp(5f))) {
                LazyColumn {
                    itemsIndexed(notesList) { index, item ->
                        val annotatedText = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Delete")
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(Dp(50f))
                        ) {
                            Text(text = item, Modifier.weight(0.85f))
                            ClickableText(
                                text = annotatedText, onClick = {
                                    notesList.remove(item)
                                },
                                modifier = Modifier.weight(0.15f)
                            )
                        }
                    }
                }
            }
        }
    }
}