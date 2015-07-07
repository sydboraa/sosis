package controllers

import play.api.mvc.{Action, Controller}

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

  def getRestaurantMenu(restaurantId:Int, uuid: String) = Action {
    Ok("")
  }
}
