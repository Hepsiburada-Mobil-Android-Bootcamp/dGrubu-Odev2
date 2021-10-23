package com.hepsiburada.dgrubuodev2.data.model
import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class Foods(
    var foodId:String?,
    var foodName:String,
    var foodCategory:String,
    var foodCalory:Int,
    var foodIngredients:String,
    var foodRecipe:String,
    var foodCookingTime:Int,
    var foodImg:String?
):Serializable {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "foodId" to foodId,
            "foodName" to foodName,
            "foodCategory" to foodCategory,
            "foodCalory" to foodCalory,
            "foodIngredients" to foodIngredients,
            "foodRecipe" to foodRecipe,
            "foodCookingTime" to foodCookingTime,
            "foodImg" to foodImg,
        )
    }
    constructor() : this("", "",
        "", 0, "",
        "",0, "")
}
