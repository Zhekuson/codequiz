package com.nuclearfoxes.data.models.tags

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class TagCountPair(@JsonProperty("tag") var tag:Tag,
                   @JsonProperty("count") var count:Int):Serializable {
}