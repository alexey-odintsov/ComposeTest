package com.example.composetest.model

enum class ContentType {
    TEXT,
    IMAGE,
    STICKER
}

abstract class Content(val type: ContentType)

data class TextContent(val value: String) : Content(ContentType.TEXT)
data class ImageContent(val value: String) : Content(ContentType.IMAGE)
data class StickerContent(val value: String) : Content(ContentType.STICKER)