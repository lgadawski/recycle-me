package resources

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class RecommendationController @Inject()(cc: ControllerComponents,
                                         facade: ResourceRecommendationFacade
                                   )(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

    def get(collectorId: Option[Int], cityId: Option[Int]): Action[AnyContent] = Action.async { implicit request =>
      facade.recommendResources(collectorId.get, cityId.get).map { ids =>
        Ok(Json.toJson(ids))
      }
    }

}
