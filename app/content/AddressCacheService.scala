package content

import javax.inject.Inject
import java.net.InetAddress

import com.google.inject.Singleton
import component.CacheService
import play.api.Logger

import scala.concurrent.Future
import scala.concurrent.duration.{Duration, MILLISECONDS}


@Singleton
class AddressCacheService @Inject() (maxAge: Long, unit: Duration, cacheService: CacheService) {

  protected val logger = Logger(this.getClass)

  /**
    * add() method must store unique elements only (existing elements must be ignored).
    * This will return true if the element was successfully added.
    *
    * @param address
    * @return
    */

  def add(address: InetAddress): Boolean = {
    val key = buildKey(address, maxAge)

    val fromCache = cacheService.get(key)

    fromCache match {
      case Some(_) => {
        fromCache
        logger.info(s"Value for key $key is $fromCache")
        false
      }
      case _       => {
        requestAddress(key, address)
        logger.info(s"Added new value $address for key $key")
        true
      }
    }
  }

  /**
    * remove() method will return true if the address was successfully removed
    *
    * @param address
    * @return
    */
  def remove(address: InetAddress): Boolean = {
    val key = buildKey(address, maxAge)
    cacheService.remove(key)
    logger.info(s"Removed key-value pair for key $key")
    true
  }

  /**
    * The peek() method will return the most recently added element,
    * null if no element exists.
    *
    * @return
    */
  def peek() = {
    import scala.collection.JavaConversions._
    import scala.concurrent.ExecutionContext.Implicits.global
    val elements = cacheService.getAll()
    elements.map(x=>x.headOption)
  }

  /**
    * take() method retrieves and removes the most recently added element
    * from the cache and waits if necessary until an element becomes available.
    *
    * @return
    */
  def take() = cacheService.mostRecent

  private def buildKey(address: InetAddress, maxAge: Long) = {
    s"${address.getHostName}_${maxAge}"
  }

  private def requestAddress(key: String, address: InetAddress): Future[Option[InetAddress]] = {
    //Do some Db or web service query to fetch
    cacheService.set(key, address, Duration(1000, MILLISECONDS))
    Future.successful(Option(address))
  }

}