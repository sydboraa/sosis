package model

import play.api.libs.functional.syntax._
import play.api.libs.json.{Writes, JsPath, Reads}
/**
 * Created by SB on 06/07/15.
 */
object ReservationModel {

  case class Reservation(var id: Option[Long], firstName: String, middleName: Option[String], lastName: String) {
    override def toString() = s"id : $id "
  }

  implicit val aliasReads: Reads[Reservation] = (
    (JsPath \ "id").readNullable[Long] and
      (JsPath \ "firstName").read[String] and
      (JsPath \ "middleName").readNullable[String]
      and (JsPath \ "lastName").read[String])(Reservation.apply _)

  implicit val aliasWrites: Writes[Reservation] = (
    (JsPath \ "id").writeNullable[Long] and
      (JsPath \ "firstName").write[String] and
      (JsPath \ "middleName").writeNullable[String] and
      (JsPath \ "lastName").write[String])(unlift(Reservation.unapply))
}
