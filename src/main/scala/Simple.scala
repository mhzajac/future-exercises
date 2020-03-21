package examples

import akka.actor.ActorSystem
import akka.stream._
import play.api.libs.ws._
import play.api.libs.ws.ahc._
import play.shaded.ahc.org.asynchttpclient._
import scala.concurrent.{ ExecutionContext, Future }

/**
 *  Write implementations for all of the methods below. None of the method calls should block at any point,
 *  and are possible using only the Scala Futures API, and additional libraries for processing data
 *  where necessary.
 *
 *  It may be helpful to break down some solutions into multiple methods, where the main implementation
 *  utilizes a few helper methods.
 *
 *  See SimpleSpec so that you can easily test them.
 */
object Simple {

    implicit val system = ActorSystem()
    implicit val materializer = SystemMaterializer(system).materializer
    val asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
        .setMaxRequestRetry(0)
        .setShutdownQuietPeriod(0)
        .setShutdownTimeout(0).build
    val asyncHttpClient = new DefaultAsyncHttpClient(asyncHttpClientConfig)
    val ws = new StandaloneAhcWSClient(asyncHttpClient)

    /**
     *  Given a URL, fetch its contents and return the text of the <title> tag as a String.
     *  If the URL returns an error code, times out, doesn't return HTML, or doesn't have a
     *  <title> tag, then a failed `Future` should be returned.
     */
    def fetchTitle(url: String)(implicit ec: ExecutionContext): Future[String] =
        ws.url(url).get().map(_.body)

    /**
     *  Do the same as `fetchTitle`, _except_ if HTML is returned but with no <title> tag, return
     *  "Untitled" instead of returning a failed `Future`.
     */
    def fetchTitleWithDefault(url: String)(implicit ec: ExecutionContext): Future[String] = ???

    /**
     *  Given three URLs, use `fetchTitle` to fetch the titles of three pages, and aggregate the results into
     *  a 3-tuple. If any individual calls to `fetchTitle` should fail, then this should return a failed `Future`.
     */
    def fetchThreeTitles(url1: String, url2: String, url3: String)(implicit ec: ExecutionContext): Future[(String, String, String)] = ???

    /**
     *  Given a list of URLs, use `fetchTitle` to fetch all of the page titles found within the URLs
     *  in parallel, and aggregate them into a list. If one call to `fetchTitle` should fail, then
     *  this should return a failed `Future`.
     */
    def fetchTitles(urls: List[String])(implicit ec: ExecutionContext): Future[List[String]] = ???

    /**
     *  Do the same as `fetchTitles`, except that any failed calls to `fetchTitle` should be discarded,
     *  so that only successfully fetched titles are returned. In this case, it should not be possible
     *  to return a failed `Future`.
     */
    def fetchTitlesDiscardErrors(urls: List[String])(implicit ec: ExecutionContext): Future[List[String]] = ???

    /**
     *  Do the same as `fetchTitles`, except that you should process each URL in sequential order,
     *  and NOT fetch a subsequent URL until the previous one has completed. That is, what's happening
     *  underneath should look something like:
     *  1. Fetch the first URL
     *  2. Process the results
     *  3. Fetch the second URL
     *  4. and so on..
     */
    def fetchTitlesSequentially(urls: List[String])(implicit ec: ExecutionContext): Future[List[String]] = ???

    /**
     *  Use `fetchTitle` to fetch the title from the `url` parameter. If it fails, attempt to fetch
     *  the title from the `alternativeUrl` parameter, instead.
     */
    def fetchTitleOrElse(url: String, alternativeUrl: String)(implicit ec: ExecutionContext): Future[String] = ???

    /**
     *  Given a Wikipedia article URL, find the first link in the "See Also" section and fetch it. Then, repeat the
     *  process until you reach a page that does not have a "See Also" section. Accumulate the result URLs into a
     *  list that you will return.
     *
     *  You can find the "See Also" links by looking at the first <ul> that follows the element with the
     *  ID "See_also". Note that the links in the article use relative URLs without a host name.
     *
     *  For example, if the first URL is: https://en.wikipedia.org/wiki/Scala_(programming_language)
     *  the resulting list should contain:
     *  https://en.wikipedia.org/wiki/Scala_(programming_language)
     *  https://en.wikipedia.org/wiki/Sbt_(software)
     *  https://en.wikipedia.org/wiki/List_of_build_automation_software
     */
    def crawl(wikiUrl: String)(implicit ec: ExecutionContext): Future[List[String]] = ???

}
