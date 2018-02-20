package resources

import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

trait ResourceRecommendationFacade {

  /**
    * Recommend resources for given collector that are available in given location. Sort list by best
    * price_per_kg x weight ratio and order by priority in cases when ratio is the same.
    *
    * @return collection of resource ids
    */
  def recommendResources(collectorId: Int, cityId: Int): Future[Seq[Int]]

}

class ResourceRecommendationFacadeImpl @Inject() (resourceRepository: ResourceRepository)
                                                 (implicit ex: ExecutionContext) extends ResourceRecommendationFacade {

  override def recommendResources(collectorId: Int, cityId: Int): Future[Seq[Int]] = {
    resourceRepository.getByCollectorByCity(collectorId, cityId)
  }
}