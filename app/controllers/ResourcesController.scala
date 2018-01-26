package controllers

import javax.inject._

import models.ResourceRepository
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ResourcesController @Inject()(repo: ResourceRepository,
                                    cc: MessagesControllerComponents
                                   )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  def get = Action.async { implicit request =>
    repo.list().map { resource =>
      Ok(Json.toJson(resource))
    }
  }

  def add = Action.async { implicit request =>
    
  }
}
