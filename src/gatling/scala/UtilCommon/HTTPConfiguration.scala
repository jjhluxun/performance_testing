package UtilCommon


import io.gatling.http.Predef._
import io.gatling.core.Predef._

object HTTPConfiguration {

  val access_token = "Bearer token"
  val hostUrl = "****"
  val sentHeaders = Map("Content-Type" -> "application/json;charset=UTF-8", "Accept-Language" -> "zh", "Authorization" -> access_token)

  val userCount = System.getProperty("userCount").toInt
  val duringTime = System.getProperty("duringTime").toInt
  val startUserCount = System.getProperty("startUserCount").toInt
  val rampDuringTime = System.getProperty("rampDuringTime").toInt
//  val hostUrl = System.getProperty("hostUrl")


  val HttpConf = http.baseUrl(hostUrl).headers(sentHeaders)

  val queryParams = Map("size" -> 10,"page" -> 0)

  val exeUser = List(rampUsersPerSec(startUserCount ) to (userCount ) during (rampDuringTime),constantUsersPerSec(userCount) during (duringTime))
}
