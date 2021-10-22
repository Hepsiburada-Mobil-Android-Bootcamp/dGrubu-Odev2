package com.hepsiburada.dgrubuodev2.data.model
import java.io.Serializable

data class Foods(
    var foodId:String,
    var foodName:String,
    var foodCategory:String,
    var foodCalory:Int,
    var foodIngredients:String,
    var foodRecipe:String,
    var foodScore:Int,
    var foodImg:String?
):Serializable {
    constructor() : this("", "",
        "", 0, "",
        "",0, "")
}