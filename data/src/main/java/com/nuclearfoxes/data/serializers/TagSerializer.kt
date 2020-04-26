package com.nuclearfoxes.data.serializers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.tags.Tag

object TagSerializer {
    var objectMapper = jacksonObjectMapper()
    fun serializeTagList(tags:ArrayList<Tag>):String{
        return objectMapper.writeValueAsString(tags)
    }
    fun serializeTag(tag: Tag):String{
        return objectMapper.writeValueAsString(tag)
    }
    fun deserializeTagList(jsonString: String): ArrayList<Tag>{
        return objectMapper.readValue<ArrayList<Tag>>(jsonString)
    }
}