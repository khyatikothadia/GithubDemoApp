package com.example.githubdemo.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * UserRepos model returned by the API.
 */
data class UserRepos(
    val name: String?,
    val description: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("stargazers_count") val count: String?,
    val forks: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(updatedAt)
        parcel.writeString(count)
        parcel.writeInt(forks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserRepos> {
        override fun createFromParcel(parcel: Parcel): UserRepos {
            return UserRepos(parcel)
        }

        override fun newArray(size: Int): Array<UserRepos?> {
            return arrayOfNulls(size)
        }
    }
}
