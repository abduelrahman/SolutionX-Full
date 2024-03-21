package am.leon.solutionx.domain

import am.leon.solutionx.domain.models.Country
import am.leon.solutionx.domain.models.Currency
import am.leon.solutionx.domain.models.Filter
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object Mockups {

    fun getCountries(context: Context): List<Country> = parseJsonFile(context, "countries.json")

    fun getCurrencies(context: Context): List<Currency> = parseJsonFile(context, "currencies.json")

    fun getFilters(context: Context): List<Filter> = parseJsonFile(context, "filters.json")

    private inline fun <reified T> parseJsonFile(
        context: Context, fileName: String
    ): List<T> {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val gson = Gson()
        val jsonObject = JSONObject(jsonString)
        val dataArray = jsonObject.getJSONArray("data")
        val itemType = object : TypeToken<T>() {}.type
        val itemList: MutableList<T> = mutableListOf()

        for (i in 0 until dataArray.length()) {
            val itemJson = dataArray.getJSONObject(i).toString()
            val item: T = gson.fromJson(itemJson, itemType)
            itemList.add(item)
        }

        return itemList
    }
}