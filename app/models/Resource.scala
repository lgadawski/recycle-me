package models

import play.api.libs.json.Json

case class Resource(id: Long, name: String, symbol: String)

object Resource {
  implicit val resourceFormat = Json.format[Resource]
}
