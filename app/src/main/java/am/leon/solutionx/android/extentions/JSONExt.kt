package am.leon.solutionx.android.extentions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

fun <M> M.toJson(): String = Gson().toJson(this)

fun <M> String.getModelFromJSON(tokenType: Type): M = Gson().fromJson(this, tokenType)

fun <M> JSONObject.getModelFromJSON(tokenType: Type): M =
    this.toString().getModelFromJSON(tokenType)

fun <M> String.getListOfModelFromJSON(tokenType: Type): ArrayList<M> =
    Gson().fromJson(this, tokenType)

fun <M> JSONArray.getListOfModelFromJSON(tokenType: Type): ArrayList<M> =
    this.toString().getListOfModelFromJSON(tokenType)

inline fun <reified T> parseJsonFile(context: Context, fileName: String): List<T> {
    val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    val dataArray = jsonObject.getJSONArray("data")
    val itemType = object : TypeToken<T>() {}.type
    val itemList: MutableList<T> = mutableListOf()

    for (i in 0 until dataArray.length()) {
        val itemJson = dataArray.getJSONObject(i).toString()
        val item: T = itemJson.getModelFromJSON(itemType)
        itemList.add(item)
    }

    return itemList
}