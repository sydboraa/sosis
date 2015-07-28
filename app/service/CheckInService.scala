package service

import dao.ReservationDao
import model.ReservationModel.Reservation

/**
 * Created by SB on 06/07/15.
 */
object CheckInService {

  def checkInToRestaurant(seatId: Int,restaurantId: Int,secureRandom: String,uuid: String,reservationKey: String): Boolean = {
    reservationKey match {
      case "0" => { //yeni rezervasyon
        try {
          ReservationDao.insertReservation(seatId,restaurantId,reservationKey)
          true
        } catch {
          case ex: Exception => {
            //TODO log basılacak
            false
          }
        }
      }
      case _ => {
        //db'den gelecek
        try {
          ReservationDao.getReservation(seatId, restaurantId, reservationKey)
          true
        } catch {
          case ex: Exception => {
            false
          }
        }
      }
    }
  }

}
