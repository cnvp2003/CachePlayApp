package content

import java.net.InetAddress

import component.CacheService
import net.sf.ehcache.{CacheManager, Cache => Ehcache}
import org.scalatest.concurrent.Eventually
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._

class AddressCacheServiceSpec extends FlatSpec with Matchers with Eventually {

  var ehcache = {
    val cacheManager = new CacheManager
    val cache = new Ehcache("test1", 1000, false, false, 0, 0)
    cacheManager.addCache(cache)
    cache
  }

  val ip = InetAddress.getByName("www.agoda.com")
  val ip1 = InetAddress.getByName("www.google.com")
  val ip2 = InetAddress.getByName("www.yahoo.com")
  val addressEhCache = new AddressCacheService(10, Duration(1000, MILLISECONDS), new CacheService(ehcache))

  "add" should "return true on adding element to cache" in {
    addressEhCache.add(ip) shouldBe(true)
    addressEhCache.add(ip1) shouldBe(true)
  }

  "peek" should "return all key-value pair from cache" in {
    addressEhCache.add(ip2) shouldBe(true)
    //AddressEhCache(10, Duration(10, MILLISECONDS), ehcache).peek() should be(Some(""))
  }

  "remove" should "remove given key and its value from cache" in {
    addressEhCache.remove(ip1) should be(true)
  }
}