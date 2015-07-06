package service

import model.ReservationModel.Reservation

/**
 * Created by SB on 06/07/15.
 */
object CheckInService {

  def checkInToRestaurant(seatId: Int,restaurantId: Int,secureRandom: String,uuid: String,reservationKey: String): (Option[Reservation],Option[Error]) = {
    (None,None)
  }

}
