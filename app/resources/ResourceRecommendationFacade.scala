package resources

import javax.inject.Inject
import price.Price

import scala.concurrent.{ExecutionContext, Future}

trait ResourceRecommendationFacade {

  /**
    * Recommend city list based on collector preference and highest possible revenue. Groups resources in given city
    * and count ration price_per_kg x weight to get based possible revenue in city.
    *
    * @return collection of address id and aggregated resource cost in that address
    */
  def recommendCityList(collectorId: Int): Future[Seq[(Int, Price)]]

  /**
    * Recommend resources for given collector that are available in given location. Sort list by best
    * price_per_kg x weight ratio and order by priority in cases when ratio is the same.
    *
    * @return collection of resource id and corresponding cost
    */
  def recommendResources(collectorId: Int, cityId: Int): Future[Seq[(Int, Price)]]

}

class ResourceRecommendationFacadeImpl @Inject()(resourceRepository: ResourceRepository)
                                                (implicit ex: ExecutionContext) extends ResourceRecommendationFacade {

  override def recommendResources(collectorId: Int, cityId: Int): Future[Seq[(Int, Price)]] = {
    val resources: Future[Seq[(Int, String, Int)]] = resourceRepository.getByCollectorByCity(collectorId, cityId)
val x: Int = null
    resources.map {
      _
        .map(a => (a._1, Price(BigDecimal(a._2)).multiply(a._3)))
        .sortBy(_._2)(Ordering[Price].reverse)
    }
  }

  override def recommendCityList(collectorId: Int): Future[Seq[(Int, Price)]] = {
    val resources: Future[Seq[(Int, String, Int)]] = resourceRepository.getByCollector(collectorId)

    resources.map {
      _
        .groupBy(_._1)
        .mapValues(_.map(e => Price(BigDecimal(e._2)).multiply(e._3)).sum)
        .map(a => (a._1, a._2))
        .toSeq
        .sortBy(_._2)(Ordering[Price].reverse)
    }
  }
}