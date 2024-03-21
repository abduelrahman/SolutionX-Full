package am.leon.solutionx.common.data.mapper

abstract class Mapper<Dto, Domain, Entity> {

    /**
     * Mapping Dto to Domain
     *
     * @param model is the dto class.
     * @return Domain class of the Dto Feature.
     */
    abstract fun dtoToDomain(model: Dto): Domain

    /**
     * Mapping Domain to Dto
     *
     * @param model is the domain class.
     * @return Dto class of the Domain Feature.
     */
    open fun domainToDto(model: Domain): Dto =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Dto list to Domain list
     *
     * @param list is the list of dto class.
     * @return Domain list of the Dto list feature.
     */
    fun dtoToDomain(list: List<Dto>?): List<Domain> = (list ?: emptyList()).map(::dtoToDomain)

    /**
     * Mapping Domain list to Dto list
     *
     * @param list is the list of domain class.
     * @return Dto list of the Domain list feature.
     */
    fun domainToDto(list: List<Domain>?): List<Dto> = (list ?: emptyList()).map(::domainToDto)


    /**
     * Mapping Entity to Domain class.
     *
     * @param model is the entity class.
     * @return Domain class.
     */
    open fun entityToDomain(model: Entity): Domain =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Domain to Entity class.
     *
     * @param model is the domain class.
     * @return Entity class.
     */
    open fun domainToEntity(model: Domain): Entity =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Entity list to Domain list
     *
     * @param list is the list of entity class.
     * @return Domain list of the Entity list feature.
     */
    fun entityToDomain(list: List<Entity>): List<Domain> = list.map(::entityToDomain)

    /**
     * Mapping Domain list to Entity list
     *
     * @param list is the list of domain class.
     * @return Entity list of the Domain list feature.
     */
    fun domainToEntity(list: List<Domain>): List<Entity> = list.map(::domainToEntity)

    /**
     * Mapping Entity to Dto class.
     *
     * @param model is the entity class.
     * @return Dto class.
     */
    open fun entityToDto(model: Entity): Dto =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Dto to Entity class.
     *
     * @param model is the dto class.
     * @return Entity class of the Dto Feature.
     */
    open fun dtoToEntity(model: Dto): Entity =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Entity list to Dto list
     *
     * @param list is the list of entity class.
     * @return Dto list of the Entity list feature.
     */
    fun entityToDto(list: List<Entity>): List<Dto> = list.map(::entityToDto)

    /**
     * Mapping Dto list to Entity list
     *
     * @param list is the list of dto class.
     * @return Entity list of the Dto list feature.
     */
    fun dtoToEntity(list: List<Dto>): List<Entity> = list.map(::dtoToEntity)
}