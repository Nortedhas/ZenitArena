package com.ageone.zenit.Models.User

import net.alexandroid.shpref.ShPref

class UserInformation {
    var address: String
        get() = ShPref.getString("userAddress", "")
        set(value) = ShPref.put("userAddress", value)
}