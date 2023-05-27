package com.example.codeblocks.ui.view.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.codeblocks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveFileDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var filename by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.enterFileName)
            )
        },
        text = {
            TextField(
                value = filename,
                onValueChange = {
                    filename = it
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.filename)
                    )
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(filename)
                },
                enabled = filename.isNotBlank()
            ) {
                Text(
                    text = stringResource(id = R.string.save)
                )
            }
        }
    )
}