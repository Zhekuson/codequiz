package com.nuclearfoxes.data.models.tags

import com.fasterxml.jackson.annotation.JsonProperty

class Tag(@JsonProperty("ID")var id:Int, @JsonProperty("Name")var name:String) {
}