package resources

import javax.inject._

import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

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
    repository.get(id).map {
      case null => NotFound
      case r => Ok(Json.toJson(r))
    }
  }

  def save: Action[JsValue] = Action(parse.json) { request =>
    val obj = request.body.as[Resource]

    repository.save(obj)

    Ok(Json.obj("message" -> "New resource saved!"))
  }

  def update(id: Long): Action[JsValue] = Action.async(parse.json) { request =>
    val obj = request.body.as[Resource]

    if (id != obj.id) {
      Future.apply(BadRequest(Json.obj("message" -> "Id from URL and body different!")))
    } else {
      repository.update(obj).map {
        case 0 => NotFound
        case _ => Ok(Json.obj("message" -> "Resource updated!"))
      }
    }
  }

  def delete(id: Long): Action[AnyContent] = Action.async {
    repository.delete(id).map {
      case 0 => NotFound
      case _ => Ok(Json.obj("message" -> "Resource deleted!"))
    }
  }

}
