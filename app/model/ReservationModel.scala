package model

import java.util.Date

import play.api.libs.functional.syntax._
import play.api.libs.json.{Writes, JsPath, Reads}
/**
 * Created by SB on 06/07/15.
 */
object ReservationModel {

  case class Reservation(id: Int, restaurantId: Int, seatId: Int, reservationCode: String, data: String, dataVersion: Int, price: Double, isArchived:Int, isActive:Int, cDate:Date, uDate:Date)

  implicit val reservationReads: Reads[Reservation] = (
    (JsPath \ "id").read[Int] and
      (JsPath \ "restaurantId").read[Int] and
      (JsPath \ "seatId").read[Int] and
      (JsPath \ "reservationCode").read[String] and
      (JsPath \ "data").read[String] and
      (JsPath \ "dataVersion").read[Int] and
      (JsPath \ "price").read[Double] and
      (JsPath \ "isArchived").read[Int] and
      (JsPath \ "isActive").read[Int] and
      (JsPath \ "cDate").read[Date] and
      (JsPath \ "uDate").read[Date])(Reservation.apply _)

  implicit val reservationWrites: Writes[Reservation] = (
    (JsPath \ "id").write[Int] and
      (JsPath \ "restaurantId").write[Int] and
      (JsPath \ "seatId").write[Int] and
      (JsPath \ "reservationCode").write[String] and
      (JsPath \ "data").write[String] and
      (JsPath \ "dataVersion").write[Int] and
      (JsPath \ "price").write[Double] and
      (JsPath \ "isArchived").write[Int] and
      (JsPath \ "isActive").write[Int] and
      (JsPath \ "cDate").write[Date] and
      (JsPath \ "uDate").write[Date])(unlift(Reservation.unapply))
}
