package dao

import play.api.Logger
import play.api.db.DB
import anorm._
import model.RestaurantModel.Restaurant

/**
 * Created by SB on 08/07/15.
 */
object RestaurantDao {

  val logger: Logger = Logger("RestaurantDaoLogger")

  def getRestaurantInfo(id: Long) : Restaurant = DB.withConnection { implicit c =>
    val rows = SQL("select * from Restaurant where id = {id} limit 1")
      .on('id -> id)

    rows().map(row => {
      val id = row[Int]("id")
      val name = row[String]("name")
      val isActive = row[Int]("isActive")
      val cDate = row[java.util.Date]("cDate")
      val uDate = row[java.util.Date]("uDate")
      Restaurant(id,name,isActive,cDate,uDate)
    }).head
  }

}
