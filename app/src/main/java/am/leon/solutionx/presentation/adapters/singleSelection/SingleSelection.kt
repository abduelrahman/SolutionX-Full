package am.leon.solutionx.presentation.adapters.singleSelection

import java.io.Serializable

interface SingleSelection : Serializable {
    var id: Int
    var name: String
    var selected: Boolean
    fun getIconRes(): Int = -1
}