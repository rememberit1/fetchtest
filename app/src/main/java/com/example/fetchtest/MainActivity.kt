package com.example.fetchtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.fetchtest.data.ItemsViewModel
import com.example.fetchtest.ui.theme.FetchTestTheme
import com.example.fetchtest.ui.theme.ViewVariables
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ItemsView()
                }
            }
        }
    }

    private fun getItemData(viewModel: ItemsViewModel) {
        lifecycleScope.launch {
            viewModel.getData()
        }
    }

    @Composable
    fun ItemsView(modifier: Modifier = Modifier, viewModel: ItemsViewModel = ItemsViewModel()) {
        getItemData(viewModel)
        Column(
            modifier = modifier
                .padding(12.dp)
        ) {
            val allData = viewModel.itemsDataObject.observeAsState().value

            /*
            Because Name variable is a string and not a number, it is sorted alphabetically and not
            by number(that is in the string). So therefor a name like "Item 466" comes before
            "Item 47"
             */
            val filteredData =
                allData?.filter { it.name != null && it.name != "" }?.groupBy { it.listId }
                    ?.toMutableMap()?.map {
                    it.value.sortedBy { it.name }
                }?.toMutableList()?.sortedBy { it.first().listId }?.flatten()

            Text(modifier = Modifier
                    .padding(bottom = 12.dp),
                text = ViewVariables.APP_TITLE,
                fontSize = ViewVariables.LARGE_FONT
            )

            LazyColumn {
                items(filteredData?.size ?: 0) { index ->
                    Text(
                        text = "ListID: " + filteredData?.get(index)?.listId.toString(),
                        fontSize = ViewVariables.SMALL_FONT
                    )
                    Text(
                        text = "Name: " + filteredData?.get(index)?.name.toString(),
                        fontSize = ViewVariables.SMALL_FONT
                    )
                    Text(
                        text = "ID: " + filteredData?.get(index)?.id.toString(),
                        fontSize = ViewVariables.SMALL_FONT
                    )
                    Divider(color = Color.Black)
                }
            }
        }
    }

}
