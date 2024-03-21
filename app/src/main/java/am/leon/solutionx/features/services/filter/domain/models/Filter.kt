package am.leon.solutionx.features.services.filter.domain.models

import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelection
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    override var id: Int,
    override var name: String,
    override var selected: Boolean
) : SingleSelection, Parcelable