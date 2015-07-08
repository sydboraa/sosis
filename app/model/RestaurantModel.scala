package model

import java.util.Date

import play.api.libs.functional.syntax._
import play.api.libs.json.{Writes, JsPath, Reads}

/**
 * Created by SB on 08/07/15.
 */
object RestaurantModel {

  case class Restaurant(id: Int, name: String, isActive: Int, cDate:Date, uDate:Date)

  implicit val reservationReads: Reads[Restaurant] = (
    (JsPath \ "id").read[Int] and
      (JsPath \ "name").read[String] and
      (JsPath \ "isActive").read[Int] and
      (JsPath \ "cDate").read[Date] and
      (JsPath \ "uDate").read[Date])(Restaurant.apply _)

  implicit val reservationWrites: Writes[Restaurant] = (
    (JsPath \ "id").write[Int] and
      (JsPath \ "name").write[String] and
      (JsPath \ "isActive").write[Int] and
      (JsPath \ "cDate").write[Date] and
      (JsPath \ "uDate").write[Date])(unlift(Restaurant.unapply))
}
