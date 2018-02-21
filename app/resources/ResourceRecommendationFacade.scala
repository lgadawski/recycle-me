package resources

import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

trait ResourceRecommendationFacade {

  /**
    * Recommend city list based on collector preference and highest possible revenue. Groups resources in given city
    * and count ration price_per_kg x weight to get based possible revenue in city.
    *
    * @return collection of address id and aggregated resource cost in that address
    */
  def recommendCityList(collectorId: Int): Future[Seq[(Int, BigDecimal)]]

  /**
    * Recommend resources for given collector that are available in given location. Sort list by best
    * price_per_kg x weight ratio and order by priority in cases when ratio is the same.
    *
    * @return collection of resource id and corresponding cost
    */
  def recommendResources(collectorId: Int, cityId: Int): Future[Seq[(Int, BigDecimal)]]

}

class ResourceRecommendationFacadeImpl @Inject()(resourceRepository: ResourceRepository)
                                                (implicit ex: ExecutionContext) extends ResourceRecommendationFacade {

  override def recommendResources(collectorId: Int, cityId: Int): Future[Seq[(Int, BigDecimal)]] = {
    val resources: Future[Seq[(Int, String, Int)]] = resourceRepository.getByCollectorByCity(collectorId, cityId)

    resources.map {
      _
        .map(a => (a._1, BigDecimal(a._2).*(a._3)))
        .sortBy(-_._2)
    }
  }

  override def recommendCityList(collectorId: Int): Future[Seq[(Int, BigDecimal)]] = {
    val resources: Future[Seq[(Int, String, Int)]] = resourceRepository.getByCollector(collectorId)

    resources.map {
      _
        .groupBy(_._1)
        .mapValues(_.map(e => BigDecimal(e._2).*(e._3)).sum)
        .map(a => (a._1, a._2))
        .toSeq
        .sortBy(-_._2)
    }
  }
}