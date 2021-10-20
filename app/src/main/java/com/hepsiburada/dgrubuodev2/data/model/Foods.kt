package com.hepsiburada.dgrubuodev2.data.model
import java.io.Serializable

data class Foods(
    var foodId:Int,
    var foodName:String,
    var foodCategory:String,
    var foodCalory:Int,
    var foodMaterial:String,
    var foodRecipe:String,
    var foodImg:String,
    var foodPrice:Int
):Serializable {
}