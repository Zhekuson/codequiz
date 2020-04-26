package com.nuclearfoxes.data.db.converters

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance


internal class ArrayListStringsConverterTest {
    companion object{
        var list:ArrayList<String> = arrayListOf("Callbacks", "Interfaces")
    }
    @BeforeEach
    fun setUp() {
    }

    @Test
    fun fromStringTest() {
        //assertEquals(list, ArrayListStringsConverter
        //    .fromString("[\"Callbacks\",\"Interfaces\"]"))
    }

    @Test
    fun fromArrayListTest() {
        //var string = ArrayListStringsConverter.fromArrayList(list)
      //  assertEquals(string, "[\"Callbacks\",\"Interfaces\"]")
    }
}