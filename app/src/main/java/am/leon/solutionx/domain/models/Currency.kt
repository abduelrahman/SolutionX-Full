package am.leon.solutionx.domain.models

import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelection
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    override var id: Int,
    override var name: String,
    val sign: String,
    val code: String,
    override var selected: Boolean
) : SingleSelection, Parcelable