import com.google.gson.annotations.SerializedName

data class Measures (

	@SerializedName("us") val us : Us,
	@SerializedName("metric") val metric : Metric
)