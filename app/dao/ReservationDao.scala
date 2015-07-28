package dao

import anorm._
import play.api.db.DB

/**
 * Created by SB on 18/07/15.
 */
object ReservationDao {

  def getReservation(seatId: Int,restaurantId: Int, reservationCode: String): String = DB.withConnection { implicit c =>
    val row = SQL("select data from Reservation where seatId = {seatId} and restaurantId = {restaurantId} and reservationCode = {reservationCode} limit 1")
      .on('seatId -> seatId,'restaurantId -> restaurantId,'reservationCode -> reservationCode).single()
    row[String]("data")
  }

  def insertReservation(seatId: Int,restaurantId: Int, reservationCode: String, data: String,dataVersion: String = 1, price :Double = 0.0,isArchived : Boolean = false, isActive : Boolean = true ) = {
    DB.withConnection { implicit c =>
      SQL("insert into Reservation(seatId, restaurantId, reservationCode, data, dataVersion, price, isArchived, isActive) values ({seatId}, {restaurantId}, {reservationCode}, {data}, {dataVersion}, {price}, {isArchived}, {isActive})")
        .on('seatId -> seatId, 'restaurantId -> restaurantId, 'reservationCode -> reservationCode, 'data -> data, 'dataVersion -> dataVersion, 'price -> price, 'isArchived -> isArchived, 'isActive -> isActive).executeInsert()
    }
  }
}
