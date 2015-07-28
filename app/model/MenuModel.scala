package model

import java.util.Date

import play.api.libs.functional.syntax._
import play.api.libs.json.{Writes, JsPath, Reads}

/**
 * Created by SB on 08/07/15.
 */
object MenuModel {

  case class Menu(id: Int, menuVersion: Int, menu: String, restaurantId: Int, cDate:Date, uDate:Date)

  implicit val reservationReads: Reads[Menu] = (
    (JsPath \ "id").read[Int] and
      (JsPath \ "menuVersion").read[Int] and
      (JsPath \ "menu").read[String] and
      (JsPath \ "restaurantId").read[Int] and
      (JsPath \ "cDate").read[Date] and
      (JsPath \ "uDate").read[Date])(Menu.apply _)

  implicit val reservationWrites: Writes[Menu] = (
    (JsPath \ "id").write[Int] and
      (JsPath \ "menuVersion").write[Int] and
      (JsPath \ "menu").write[String] and
      (JsPath \ "restaurantId").write[Int] and
      (JsPath \ "cDate").write[Date] and
      (JsPath \ "uDate").write[Date])(unlift(Menu.unapply))
}
