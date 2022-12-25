package com.ibm.rides.ui.vehicle.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Vehicle(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("vin") var vin: String? = null,
    @SerializedName("make_and_model") var makeAndModel: String? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("transmission") var transmission: String? = null,
    @SerializedName("drive_type") var driveType: String? = null,
    @SerializedName("fuel_type") var fuelType: String? = null,
    @SerializedName("car_type") var carType: String? = null,
    /*@SerializedName("car_options") var carOptions: ArrayList<String> = arrayListOf(),
    @SerializedName("specs") var specs: ArrayList<String> = arrayListOf(),*/
    @SerializedName("doors") var doors: Int? = null,
    @SerializedName("mileage") var mileage: Int? = null,
    @SerializedName("kilometrage") var kilometrage: Int? = null,
    @SerializedName("license_plate") var licensePlate: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(uid)
        parcel.writeString(vin)
        parcel.writeString(makeAndModel)
        parcel.writeString(color)
        parcel.writeString(transmission)
        parcel.writeString(driveType)
        parcel.writeString(fuelType)
        parcel.writeString(carType)
        parcel.writeValue(doors)
        parcel.writeValue(mileage)
        parcel.writeValue(kilometrage)
        parcel.writeString(licensePlate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }
    }
}