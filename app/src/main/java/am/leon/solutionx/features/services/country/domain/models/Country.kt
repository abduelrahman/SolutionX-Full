package am.leon.solutionx.features.services.country.domain.models

import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelection
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    override var id: Int,
    override var name: String,
    val currency: String,
    val code: String,
    val phoneCode: String,
    val flag: String,
    override var selected: Boolean
) : SingleSelection, Parcelable