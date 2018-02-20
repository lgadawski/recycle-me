package resources

import com.google.inject.AbstractModule

class Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[ResourceRecommendationFacade]).to(classOf[ResourceRecommendationFacadeImpl])
  }
}
