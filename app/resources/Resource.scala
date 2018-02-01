package resources

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{JsPath, Json, OFormat, Reads}

case class Resource(id: Long, name: String, symbol: String)

object Resource {

  implicit val resourceFormat: OFormat[Resource] = Json.format[Resource]

  implicit val resourceReads: Reads[Resource] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "name").read[String] and
      (JsPath \ "symbol").read[String]
    ) (Resource.apply _)
}
