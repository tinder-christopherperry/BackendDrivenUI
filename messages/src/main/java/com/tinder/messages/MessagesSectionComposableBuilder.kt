package com.tinder.messages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tinder.plugins.Component
import com.tinder.plugins.ComposableBuilder
import com.tinder.plugins.Content
import com.tinder.plugins.Message

internal class MessagesSectionComposableBuilder: ComposableBuilder {

    @Composable
    override fun BuildComposable(component: Component) {
        val content = component.content as Content.MessagesSectionContent
        MessagesSection(content)
    }

    @Composable
    private fun MessagesSection(content: Content.MessagesSectionContent) {
        Card(
            shape = MaterialTheme.shapes.small,
            elevation = 8.dp,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = content.sectionTitle,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                MessageRow(content.messages[0])
                MessageRow(content.messages[1])
                MessageRow(content.messages[2])

            }
        }
    }

    @Composable
    private fun MessageRow(message: Message) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.robert_downey),
                contentDescription = null,
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier
                    .width(12.dp)
                    .wrapContentHeight()
            )

            Column {
                Text(
                    text = message.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(message.subtitle)
            }
        }
    }

    @Preview
    @Composable
    private fun MessageRowPreview() {
        MessageRow(Message(title = "Title", subtitle = "Subtitle"))
    }

    @Preview
    @Composable
    private fun MessagesSectionPreview() {
        MessagesSection(
            Content.MessagesSectionContent(
            sectionTitle = "Section Title",
                messages = listOf(
                    Message("Title One", "Content one"),
                    Message("Title Two", "Content two"),
                    Message("Title Three", "Content three")
                )
        ))
    }
}