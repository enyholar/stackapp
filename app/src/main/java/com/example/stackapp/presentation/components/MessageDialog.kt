package com.example.stackapp.presentation.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.stackapp.R

@Composable
 fun MessageDialog(
    onDismiss: (() -> Unit)? = null,
    title: String =  stringResource(id = R.string.error_title_default),
    message: String,
    primaryText: String,
    primaryAction: () -> Unit,
    secondaryText: String? = null,
    secondaryAction: (() -> Unit)? = null,
) {

    Dialog(onDismissRequest  = onDismiss ?: secondaryAction ?: primaryAction,
    ) {
        Card(
            elevation = 8.dp, shape = RoundedCornerShape(12.dp)
        ) {

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = message,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))


                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    onClick = {
                    primaryAction()
                }) {
                    Text(text = primaryText)
                }

                if (secondaryText != null && secondaryAction != null) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                        onClick = {
                        secondaryAction()
                    }) {
                        Text(text = secondaryText)
                    }
                }
            }
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=840,unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=840,height=800,unit=dp,dpi=480")
@Preview(name = "tablet", device = "spec:shape=Normal,width=600,height=800,unit=dp,dpi=480")
@Composable
private fun UserSearchScreenPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        MessageDialog(onDismiss = {},
            title = "Pencil",
            message = "I am ok",
            primaryText = "Ok",
            primaryAction = {},
            secondaryText = "Cancel",
            secondaryAction = {})
    }
}