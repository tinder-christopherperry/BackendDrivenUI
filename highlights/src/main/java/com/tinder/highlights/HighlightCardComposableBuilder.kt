package com.tinder.highlights

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tinder.plugins.Component
import com.tinder.plugins.ComposableBuilder
import com.tinder.plugins.Content

internal class HighlightCardComposableBuilder : ComposableBuilder {

    @Composable
    override fun BuildComposable(component: Component) {
        val content = component.content as Content.HighlightCardContent
        HighlightCard(content)
    }

    @Composable
    private fun HighlightCard(content: Content.HighlightCardContent) {
        Card(
            shape = MaterialTheme.shapes.small,
            elevation = 8.dp,
            modifier = Modifier
                .height(164.dp)
                .width(328.dp)
                .padding(6.dp)
        ) {
            ConstraintLayout {
                val (image, leftText, topRightText, midRightText, button) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.black_widow),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .constrainAs(image) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        },
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = content.leftText,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .constrainAs(leftText) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(image.start)
                            end.linkTo(image.end)
                        }
                )

                Text(
                    text = content.topRightText,
                    modifier = Modifier.constrainAs(topRightText) {
                        bottom.linkTo(midRightText.top)
                        top.linkTo(parent.top)
                        start.linkTo(image.end)
                        end.linkTo(parent.end)
                    }
                )

                Text(
                    text = content.midRightText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .constrainAs(midRightText) {
                            top.linkTo(topRightText.bottom)
                            bottom.linkTo(button.top)
                            start.linkTo(image.end)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                )

                Button(
                    onClick = {},
                    modifier = Modifier.constrainAs(button) {
                        top.linkTo(midRightText.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(image.end)
                        end.linkTo(parent.end)
                    }
                ) {
                    Text(content.buttonText)
                }
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewHighlightCard() {
        val content = Content.HighlightCardContent(
            leftText = "Left Text",
            topRightText = "Top Right Text",
            midRightText = "Really Really Long Mid Right Text",
            buttonText = "Button Text"
        )

        HighlightCard(content)
    }

}