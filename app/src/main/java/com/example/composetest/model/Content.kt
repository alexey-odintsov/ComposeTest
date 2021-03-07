package com.example.composetest.model

enum class ContentType {
    TEXT,
    IMAGE,
    STICKER
}

interface Content

data class TextContent(val value: String) : Content
data class ImageContent(val value: String) : Content
data class StickerContent(val value: String) : Content