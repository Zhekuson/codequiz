package com.nuclearfoxes.data.models.tags

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class Tag(@JsonProperty("id")var id:Int?, @JsonProperty("name")var name:String?):Serializable{
}