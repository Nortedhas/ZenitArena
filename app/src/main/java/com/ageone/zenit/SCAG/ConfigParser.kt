package com.ageone.zenit.SCAG

import com.ageone.zenit.Application.utils
import com.ageone.zenit.SCAG.Parser
import org.json.JSONObject

fun Parser.config(json: JSONObject) {
	json.optJSONArray("Config")?.optJSONObject(0)?.let { userJson ->
		utils.config.name = userJson.optString("name", "")
	}
}