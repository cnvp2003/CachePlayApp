package component

import net.sf.ehcache.{CacheManager, Element, Cache => Ehcache}
import org.scalatest.concurrent.Eventually
import org.scalatest.{FlatSpec, Matchers}

class CacheServiceSpec extends FlatSpec with Matchers with Eventually {

/*
  var ehcache = {
    val cacheManager = new CacheManager
    val cache = new Ehcache("test", 1000, false, false, 0, 0)
    cacheManager.addCache(cache)
    cache
  }

  val addressEhCache = new CacheService(ehcache)

  "get" should "return the value from Ehcache" in {
    ehcache.put(new Element("key1", 123))

    addressEhCache.get("key1").map { result =>
      result should be(Some(123))
    }
  }

  it should "return None if the given key does not exist" in {
    addressEhCache.get("nokey").map { result =>
      result should be(None)
    }
  }

  "put" should "put given key-value pair in cache" in {
    addressEhCache.put("key1", 123, None)
    ehcache.get("key1").getObjectValue should be(123)
  }

  it should "put given key-value pair in cache with time" in {
    addressEhCache.put("key1", 123, Some(1 second))
    ehcache.get("key1").getObjectValue should be(123)

    // Should expire after 1 second
    eventually(timeout(Span(2, Seconds))) {
      ehcache.get("key1") should be(null)
    }
  }*/
}