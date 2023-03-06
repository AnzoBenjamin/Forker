import com.google.gson.annotations.SerializedName

data class AnalyzedInstructions (

	@SerializedName("name") val name : String,
	@SerializedName("steps") val steps : List<Steps>
)