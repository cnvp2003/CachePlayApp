package component

import javax.inject.Inject

import com.google.inject.Singleton
import net.sf.ehcache.{Ehcache, Element}
import play.api.Logger

import scala.concurrent.Future
import scala.concurrent.duration.Duration


@Singleton
class CacheService @Inject() (cache: Ehcache){

  protected val logger = Logger(this.getClass)

  def set(key: String, value: Any, ttl: Duration) = {
    val element = new Element(key, value)
    element.setTimeToLive(ttl.toSeconds.toInt)
    cache.put(element)

    logger.info(s"Value $value put in cache for key $key")
  }

  def get(key: String): Option[Any] = {
    Option(cache.get(key)).map(_.getObjectValue)
  }

  def remove(key: String) = Future.successful(cache.remove(key))

  def removeAll() = Future.successful(cache.removeAll())

  def getAll() = Future.successful(cache.getAll(cache.getKeys).values)

  def mostRecent() = {
    val key = cache.getKeys.get(0)
    cache.remove(key)
  }

  def close(): Unit = {
    // Nothing to do
  }

/*  private[component] def exceptionAwareCacheAccess[Element]( key: String): Option[Element] = {
    Try[Option[Element]] {
      Option(cache.get(key))
    }.recover {
      case e: InvalidClassException =>
        cache.remove(key)
        None
      case e =>
        logger.error("Unexpected error when retrieving object from cache", e)
        cache.remove(key)
        None
    }.get
  }*/
}