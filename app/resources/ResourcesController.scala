package resources

import javax.inject._

import play.api.Logger
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ResourcesController @Inject()(repository: ResourceRepository,
                                    cc: ControllerComponents
                                   )(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def getAll: Action[AnyContent] = Action.async { implicit request =>
    repository.list().map { resource =>
      Ok(Json.toJson(resource))
    }
  }

  def get(id: Long): Action[AnyContent] = Action.async { implicit request =>
    repository.get(id).map { resource =>
      Ok(Json.toJson(resource))
    }.recover {
      case e: Exception =>
        Logger.debug(e.printStackTrace().toString)

        NotFound
    }
  }

  def save: Action[JsValue] = Action(parse.json) { request =>
    val obj = request.body.as[Resource]

    repository.save(obj)

    Ok(Json.obj("status" -> "OK", "message" -> "New resource saved!"))
  }

  def update(id: Long): Action[JsValue] = Action(parse.json) { request =>
    val obj = request.body.as[Resource]

    if (id != obj.id) {
      BadRequest(Json.obj("status" -> "Bad_request", "message" -> "Id from URL and body different!"))
    } else {
      repository.update(obj)

      Ok(Json.obj("status" -> "OK", "message" -> "Resource updated!"))
    }
  }

}
