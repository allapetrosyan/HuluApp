package huluapplication.huluapplication.huluapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class FilmItem(
    @SerializedName("item_title")
    val filmNameText:String,
    @SerializedName("small_ic")
    val filmSmallImg: String,
    @SerializedName("big_ic")
    val filmBigImg:String,
    @SerializedName("sub_title")
    val aboutFilm:String,
    @SerializedName("reating")
    val filmRating:String,
    @SerializedName("realise_date")
    val filmReleaseDate:String,
    @SerializedName("genres")
    val filmGenres:String,
    @SerializedName("video_url")
    val filmYoutube:String,
    var isItemFavorite:Boolean = false
): Parcelable