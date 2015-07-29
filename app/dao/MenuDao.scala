package dao

import anorm._
import model.MenuModel.Menu
import model.RestaurantModel.Restaurant
import play.api.Logger
import play.api.Play.current
import play.api.db.DB

/**
 * Created by SB on 08/07/15.
 */
object MenuDao {

  val logger: Logger = Logger("MenuDaoLogger")

  def getMenu(restaurantId: Long,uuid: String) : Menu = DB.withConnection { implicit c =>
    val rows = SQL("SELECT * FROM `Menu` inner join Restaurant on Restaurant.id = Menu.restaurantId where Restaurant.id = {restaurantId} ORDER by menuVersion DESC limit 1")
      .on('restaurantId -> restaurantId)

    rows().map(row => {
      val id = row[Int]("id")
      val menuVersion = row[Int]("menuVersion")
      val menu = row[String]("menu")
      val restaurantId = row[Int]("restaurantId")
      val cDate = row[java.util.Date]("cDate")
      val uDate = row[java.util.Date]("uDate")
      Menu(id,menuVersion,menu,restaurantId,cDate,uDate)
    }).head
  }

}
