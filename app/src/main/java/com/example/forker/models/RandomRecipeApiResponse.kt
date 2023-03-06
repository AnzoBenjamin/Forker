import com.google.gson.annotations.SerializedName

data class RandomRecipeApiResponse (

	@SerializedName("recipes") val recipes : List<Recipes>
)