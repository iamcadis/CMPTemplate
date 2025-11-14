package com.design.system.extension

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

inline fun <T> LazyListScope.section(
    title: String,
    items: List<T>,
    shape: Shape = RoundedCornerShape(8.dp),
    noinline header: @Composable LazyItemScope.() -> Unit = {
        SectionTitle(title = title)
    },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) {
    item(
        key = "section_${title.hashCode()}",
        contentType = "section_card"
    ) {
        Column {
            header()
            Card(shape = shape) {
                Column {
                    items.forEachIndexed { index, item ->
                        itemContent(item)
                        if (index < items.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

inline fun LazyListScope.section(
    title: String,
    shape: Shape = RoundedCornerShape(8.dp),
    noinline header: @Composable LazyItemScope.() -> Unit = {
        SectionTitle(title = title)
    },
    crossinline itemContent: @Composable LazyItemScope.() -> Unit,
) {
    item(
        key = "section_${title.hashCode()}",
        contentType = "section_card"
    ) {
        Column {
            header()
            Card(shape = shape, modifier = Modifier.fillMaxWidth()) {
                itemContent()
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 16.dp)
            .padding(top = 4.dp, bottom = 8.dp)
    )
}