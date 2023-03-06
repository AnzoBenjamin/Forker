import com.google.gson.annotations.SerializedName

data class Us (

	@SerializedName("amount") val amount : Double,
	@SerializedName("unitShort") val unitShort : String,
	@SerializedName("unitLong") val unitLong : String
)