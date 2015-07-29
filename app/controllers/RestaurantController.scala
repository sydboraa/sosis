package controllers

import dao.MenuDao
import model.MenuModel.Menu
import play.api.Logger
import play.api.libs.json.{Json, JsValue}
import play.api.mvc.{BodyParser, Action, Controller}
import service.{MenuService, CheckInService}

import scala.util.{Failure, Success, Try}

/**
 * Created by SB on 07/07/15.
 * POST /api/1/getRestaurantMenu
Input:
{
  restaurantId: int
  uuid: string
}
Output:
{
 v: versionNumber,int
 categories: [
   {
 	id: int
 	name: string
 	items: [iid1,iid2,...]
   }
   ...
 ]
 items: {
   iid1: {
 	id: int
 	name: string
 	shortName: string
 	description: string
 	shortDescription: string
 	price: double
   }
 }
 paymentTypes: {"cash", "debitCard", "ticketCard", etc. }
}
Errors:
NotFound, OldVersion, Unknown


 */
class RestaurantController extends Controller {

  val logger: Logger = Logger("RestaurantControllerLogger")

  val tryJsonParser: BodyParser[Try[JsValue]] = parse.tolerantText.map(text => Try(Json.parse(text)))

  def getRestaurantMenu() =
    Action(tryJsonParser) { request =>
      val bodyAsJson: Try[JsValue] = request.body
      bodyAsJson match {
        case Success(js) => {
          try {
            val restaurantId : Int = (js \ "restaurantId").as[Int]
            val uuid : String = (js \ "uuid").as[String]
            try {
              val menu : String = MenuService.getMenu(restaurantId,uuid)
              Ok(Json.toJson(menu))
            } catch {
              case ex: Exception => { //there is no menu now
                BadRequest(Json.toJson("Something is wrong. Please try again later!"))
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
