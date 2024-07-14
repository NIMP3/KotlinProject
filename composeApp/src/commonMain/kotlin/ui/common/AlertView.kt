import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AlertView(show: Boolean, onCancel: () -> Unit, onConfirm: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    if (!show) return

    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Text(
                text = "Write something",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray, RoundedCornerShape(10))
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        onCancel()
                        text = ""
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(24),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .weight(1f)
                ) {
                    Text(text = "CANCEL")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        onConfirm(text)
                        text = ""
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    shape = RoundedCornerShape(24),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .weight(1f)
                ) {
                    Text(text = "CONFIRM")
                }
            }
        }
    }
}