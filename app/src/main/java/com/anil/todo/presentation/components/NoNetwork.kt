package com.anil.todo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.anil.todo.util.Dimens

@Composable
fun NoNetwork() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.Extra_Large),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(Dimens.Icon_Size)
                    .padding(bottom = Dimens.Base),
                painter = painterResource(com.anil.todo.R.drawable.ic_offline),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
            Text(
                text = stringResource(id = com.anil.todo.R.string.please_check_network_connection),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}