import com.example.forker.models.Ingredient
import com.google.gson.annotations.SerializedName

data class Steps (

	@SerializedName("number") val number : Int,
	@SerializedName("step") val step : String,
	@SerializedName("ingredients") val ingredients : List<Ingredient>,
	@SerializedName("equipment") val equipment : List<Equipment>
)