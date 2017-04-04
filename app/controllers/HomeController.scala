package controllers

import java.net.InetAddress
import javax.inject._

import component.CacheService
import content.AddressCacheService
import net.sf.ehcache.{CacheManager, Cache => Ehcache}
import play.api.mvc._

import scala.concurrent.duration.{Duration, MILLISECONDS}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action { implicit request =>
    /*var ehcache = {
      val cacheManager = new CacheManager
      val cache = new Ehcache("test1", 1000, false, false, 0, 0)
      cacheManager.addCache(cache)
      cache
    }
    val ip = InetAddress.getByName("www.agoda.com")
    val ip1 = InetAddress.getByName("www.google.com")
    val ip2 = InetAddress.getByName("www.yahoo.com")
    val addressEhCache = new CacheService(ehcache)
    new AddressCacheService(100L, Duration(100, MILLISECONDS), addressEhCache).add(ip)*/

    Ok(views.html.index())
  }
}
