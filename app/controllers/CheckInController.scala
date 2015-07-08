package controllers

import model.ReservationModel.Reservation
import play.api.Logger
import play.api.libs.json.{Json, JsValue}
import play.api.mvc.{BodyParser, Action, Controller}
import service.CheckInService
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by SB on 06/07/15.
  POST /api/1/checkIn
Input:
{
  seatId: int
  restaurantId: int
  secureRandom: string
  uuid: string
  reservationKey: string || null
}
Output:
{
  reservationId: <random-string>
  reservationKey: string
  menuVersion: int
  orders: {ORDERSOBJ}
}
Errors:
Authantication, NotFound, OldVersion, Unknown
 */
class CheckInController extends Controller {

  val logger: Logger = Logger("CheckInControllerLogger")

  val tryJsonParser: BodyParser[Try[JsValue]] = parse.tolerantText.map(text => Try(Json.parse(text)))

  def checkIn() =
    Action(tryJsonParser) { request =>
      val bodyAsJson: Try[JsValue] = request.body
      bodyAsJson match {
        case Success(js) => {
          try {
            val seatId : Int = (js \ "seatId").as[Int]
            val restaurantId : Int = (js \ "restaurantId").as[Int]
            val secureRandom : String = (js \ "secureRandom").as[String]
            val uuid : String = (js \ "uuid").as[String]
            val reservationKeyAsOpt : Option[String] = (js \ "reservationKey").asOpt[String]
            val reservationAsOpt: (Option[Reservation],Option[Error]) = reservationKeyAsOpt match {
              //existing table check-in
              case Some(reservationKey) => {
                //var olan siparişlerin listesi gidecek
                CheckInService.checkInToRestaurant(seatId,restaurantId,secureRandom,uuid,reservationKey)
              }
              //new check-in
              case None => {
                CheckInService.checkInToRestaurant(seatId,restaurantId,secureRandom,uuid,"0")
              }
            }
            reservationAsOpt match {
              case (Some(reservation),None) => {
                Ok(Json.toJson(reservation))
              }
              case (Some(reservation),Some(error)) => {
                logger.error(error.toString)
                BadRequest(Json.toJson(error.toString))
              }
              case (None, Some(error)) => {
                logger.error(error.toString)
                BadRequest(Json.toJson(error.toString))
              }
              case (None,None) => {
                BadRequest(Json.toJson("Something is wrong!"))
              }
            }
          }catch {
            // must be a parse error
            case ex: Exception => {
              logger.error("Missing/Invalid Field" + ex.toString)
              BadRequest(Json.toJson("Missing/Invalid Field exception!"))
            }
          }
        }
        case Failure(ex) => {
          logger.error("Missing/Invalid Field" + ex.toString)
          BadRequest(Json.toJson("Missing/Invalid Field failure!"))
        }
      }
    }
}

