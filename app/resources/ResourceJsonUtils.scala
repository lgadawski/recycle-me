package resources

import java.sql.Timestamp

import model.Tables._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.Writes._
import play.api.libs.json._

object ResourceJsonUtils {

  implicit val rds: Reads[Timestamp] = (__ \ "time").read[Long].map { long => new Timestamp(long) }

//  implicit val wrs: Writes[Timestamp] = (__ \ "time").write[Long].contramap { (a: Timestamp) => a.getTime }

//  implicit val fmt: Format[Timestamp] = Format(rds, wrs)

  implicit val resourceReads: Reads[ResourcesRow] = (
    (JsPath \ "id").read[Int] and
      (JsPath \ "resourceTypeId").read[Int] and
      (JsPath \ "pricePerKg").read[String] and
      (JsPath \ "weight").read[Int] and
      (JsPath \ "addressId").read[Int] and
      (JsPath \ "ownerId").read[Int] and
      (JsPath \ "createTs").read[Timestamp] and
      (JsPath \ "collectedTs").readNullable[Timestamp] and
      (JsPath \ "deletedTs").readNullable[Timestamp]
    ) (ResourcesRow.apply _)

  implicit val resourceWrites: Writes[ResourcesRow] = (
    (JsPath \ "id").write[Int] and
      (JsPath \ "resourceTypeId").write[Int] and
      (JsPath \ "pricePerKg").write[String] and
      (JsPath \ "weight").write[Int] and
      (JsPath \ "addressId").write[Int] and
      (JsPath \ "ownerId").write[Int] and
      (JsPath \ "createTs").write[Timestamp] and
      (JsPath \ "collectedTs").writeNullable[Timestamp] and
      (JsPath \ "deletedTs").writeNullable[Timestamp]
    ) (unlift(ResourcesRow.unapply))

  implicit val frr: Format[ResourcesRow] = Format(resourceReads, resourceWrites)
}
