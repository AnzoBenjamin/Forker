import com.google.gson.annotations.SerializedName

data class Temperature (

	@SerializedName("number") val number : Int,
	@SerializedName("unit") val unit : String
)