package com.anil.todo.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import com.anil.todo.R
import com.anil.todo.data.local.TodoEntity
import com.anil.todo.domain.model.Todo
import com.anil.todo.presentation.components.NoNetwork
import com.anil.todo.util.Dimens

@Composable
fun TodoScreen(
    onCompleteState: (Boolean) -> Unit
) {
    val viewModel = hiltViewModel<TodoViewModel>()
    val uiState = viewModel.uiState
    Scaffold (
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.Top_Bar_Size)
                    .background(color = Color(0xFF6650a4)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .padding(horizontal = Dimens.Base),
                    text = stringResource(id = R.string.my_todo_list),
                    fontSize = Dimens.Top_Bar_Font_Size,
                    color = Color.White
                )
            }
        },
        content = {innerPadding ->
            Box (modifier = Modifier.padding(innerPadding)){
                if (uiState.offline) {
                    NoNetwork()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        items(uiState.list) { item ->
                            TodoItem(
                                item = item,
                                onCompleteState = {
                                    onCompleteState.invoke(it)
                                    viewModel.updateNote(
                                        TodoEntity(
                                            id = item.id,
                                            userId = item.userId,
                                            title = item.title,
                                            completed = it
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    )

}

@Composable
fun TodoItem(
    item: Todo,
    onCompleteState: (Boolean) -> Unit
) {

    var checked by remember { mutableStateOf(item.completed) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Base)
            .clickable {
                checked = !checked
                onCompleteState.invoke(checked)
            },
    ) {


        Row (
            modifier = Modifier
                .fillMaxWidth(),
        ){

            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                    onCompleteState.invoke(checked)
                }
            )

            Text(
                modifier = Modifier
                    .padding(start = Dimens.Base)
                    .align(Alignment.CenterVertically),
                text = item.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = if (checked) TextStyle(textDecoration = TextDecoration.LineThrough)
                else TextStyle()
            )

        }

        Divider(
            modifier = Modifier,
            thickness = Dimens.Divider_Thickness
        )

    }
}