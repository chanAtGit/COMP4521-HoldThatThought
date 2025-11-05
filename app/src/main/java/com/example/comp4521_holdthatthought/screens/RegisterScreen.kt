package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.ImageFromDrawableName
import com.example.comp4521_holdthatthought.ui.components.Logo

@Composable
fun RegisterScreen(onDone: () -> Unit = {}, onBack: () -> Unit = {}) {
    val (name, setName) = remember { mutableStateOf("John") }
    val (email, setEmail) = remember { mutableStateOf("Johndoe@email.com") }
    val (phone, setPhone) = remember { mutableStateOf("(+1) 234 567 890") }
    val (password, setPassword) = remember { mutableStateOf("password") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
        }
        Spacer(Modifier.height(4.dp))
        Logo()
        Spacer(Modifier.height(24.dp))
        ImageFromDrawableName(name = "img_register_illustration", modifier = Modifier.fillMaxWidth().height(160.dp))
        Spacer(Modifier.height(24.dp))
        Text("Name")
        OutlinedTextField(value = name, onValueChange = setName, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("Email")
        OutlinedTextField(value = email, onValueChange = setEmail, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("Phone Number")
        OutlinedTextField(value = phone, onValueChange = setPhone, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("Password")
        OutlinedTextField(value = password, onValueChange = setPassword, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        PrimaryButton(text = "Register", onClick = onDone)
    }
}
