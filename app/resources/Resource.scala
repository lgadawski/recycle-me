package resources

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.Writes._
import play.api.libs.json._

case class Resource(id: Long, name: String, symbol: String)

object Resource {

  implicit val resourceFormat: OFormat[Resource] = Json.format[Resource]

  implicit val resourceReads: Reads[Resource] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "name").read[String] and
      (JsPath \ "symbol").read[String]
    ) (Resource.apply _)

  implicit val resourceWrites: Writes[Resource] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String] and
      (JsPath \ "symbol").write[String]
    ) (unlift(Resource.unapply))
}
