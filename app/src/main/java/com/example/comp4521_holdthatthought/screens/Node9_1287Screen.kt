package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.AvatarFromDrawableName
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme

@Composable
fun Node9_1287Screen(onOpenSettings: () -> Unit = {}) {
    val (name, setName) = remember { mutableStateOf("John") }
    val (email, setEmail) = remember { mutableStateOf("Johndoe@email.com") }
    val (phone, setPhone) = remember { mutableStateOf("(+1) 234 567 890") }
    val (password, setPassword) = remember { mutableStateOf("password") }
    val (showPwd, setShowPwd) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "My Account", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        AvatarFromDrawableName(name = "img_avatar_default", modifier = Modifier.size(96.dp))
        Spacer(Modifier.height(8.dp))
        Text("Change Picture", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))

        Spacer(Modifier.height(16.dp))
        Text("Name", style = MaterialTheme.typography.labelMedium, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = name, onValueChange = setName, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("Email", style = MaterialTheme.typography.labelMedium, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = setEmail, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("Phone Number", style = MaterialTheme.typography.labelMedium, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phone, onValueChange = setPhone, modifier = Modifier.fillMaxWidth(), leadingIcon = { Text("📞") })
        Spacer(Modifier.height(8.dp))
        Text("Password", style = MaterialTheme.typography.labelMedium, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPwd) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { setShowPwd(!showPwd) }) {
                    Icon(if (showPwd) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = "Toggle password")
                }
            }
        )

        Spacer(Modifier.height(16.dp))
        PrimaryButton(text = "Save Changes", onClick = { /* TODO hook to backend */ })
        Spacer(Modifier.height(12.dp))
        com.example.comp4521_holdthatthought.ui.components.SecondaryButton(text = "Settings", onClick = onOpenSettings)
    }
}

// No preview needed
