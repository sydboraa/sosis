package service

import dao.MenuDao
import model.MenuModel.Menu

/**
 * Created by SB on 29/07/15.
 */
object MenuService {

  def getMenu(restaurantId: Int, uuid: String) : String = {
    try {
      val menu : Menu = MenuDao.getMenu(restaurantId,uuid)
      menu.menu
    } catch {
      case ex : Exception => {
        //TODO log basılacak
        throw new RuntimeException("Something is wrong. Please contact the restaurant admin :)")
      }
    }
  }

}
