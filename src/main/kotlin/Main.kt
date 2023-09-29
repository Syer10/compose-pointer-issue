import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TestButton(text: String, onClick: () -> Unit) {
    val displayHover = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .onPointerEvent(PointerEventType.Enter) {
                displayHover.value = true
            }
            .onPointerEvent(PointerEventType.Exit) {
                displayHover.value = false
            },
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            Text(text)
        }
        DropdownMenu(
            displayHover.value,
            onDismissRequest = {
                displayHover.value = false
            },
            modifier = Modifier.background(MaterialTheme.colors.background),
            offset = DpOffset(x = 0.dp, y = 0.dp),
        ) {
            Text(
                text = "My tooltip",
            )
        }
    }
}

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Box(Modifier.pointerHoverIcon(PointerIcon.Default)) {
            TestButton(
                text,
                onClick = {
                    text = "Hello, Desktop!"
                },
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
