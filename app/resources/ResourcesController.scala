package resources

import javax.inject._

import play.api.libs.json.Writes._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ResourcesController @Inject()(repository: ResourceRepository,
                                    cc: ControllerComponents
                                   )(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  import ResourceJsonUtils._

  def getAll: Action[AnyContent] = Action.async { implicit request =>
    repository.list().map { resource =>
      Ok(Json.toJson(resource))
    }
  }

  def get(id: Int): Action[AnyContent] = Action.async { implicit request =>
    repository.get(id).map {
      case null => NotFound
      case resource => Ok(Json.toJson(resource))
    }
  }

//  def save: Action[JsValue] = Action.async(parse.json) { request =>
//    val obj = request.body.as[ResourcesRow]
//
//    repository.save(obj).map { r =>
//      Ok(Json.toJson(r))
//    }
//  }
//
//  def update(id: Int): Action[JsValue] = Action.async(parse.json) { request =>
//    val obj = request.body.as[Tables.ResourcesRow]
//
//    if (id != obj.id) {
//      Future.apply(BadRequest(Json.obj("message" -> "Id from URL and body different!")))
//    } else {
//      repository.update(obj).map {
//        case 0 => NotFound
//        case _ => Ok(Json.toJson(obj))
//      }
//    }
//  }

  def delete(id: Int): Action[AnyContent] = Action.async {
    repository.delete(id).map {
      case 0 => NotFound
      case _ => Ok(Json.obj("message" -> "Resource deleted!"))
    }
  }

}
