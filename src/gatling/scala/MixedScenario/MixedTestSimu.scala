package MixedScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MixedTestSimu extends Simulation {

  object General {

    val accessQR =
      exec(http("通行码")
        .post("/api/travel/v1/customer/travel-permissions/general-access")
        .body(StringBody("""{}""")).asJson
        .check(status.is(200)))
        .exec(http("通行楼层")
          .get("/api/travel/v1/customer/travel-permissions")
          .check(status.is(200)))
        .exec(http("访客码列表")
          .get("/api/travel/v1/customer/visitations/passcode/navigation")
          .check(status.is(200)))
        .exec(http("灵活空间码列表").get("/api/order/v1/customer/flexible-space-orders/passcode/navigation")
          .check(status.is(200)))
        .exec(http("活动码列表").get("/api/community/v1/customer/activity-sign-up/passcode/navigation")
          .check(status.is(200)))
  }


  object MainPage {
    val mainPage =
      exec(http("用户信息").get("/api/auth/v1/customer/users/current")
        .check(status.is(200)))
        .exec(http("可读消息").get("/api/support/v1/reminder/customer").queryParam("type", "NOTIFICATION").check(status.is(200)))
  }

  object Notification {
    val nofication =
      exec(http("通知读取").get("/api/support/v1/notifications")
        .queryParam("page", 0).queryParam("size", 10).check(status.is(200)))
        .exec(http("空间券").get("/api/travel/v1/customer/office-tenants/coupon-info").check(status.is(200)))
  }

  object Order {
    val order =
      exec(http("订单列表").get("/api/order/v1/customer/brief-orders").queryParam("status", "PENDING").queryParam("page", 0).queryParam("size", 10)
        .check(status.is(200)).check(jsonPath("$.content[0].orderNo").saveAs("id")))
        .exec(http("订单详情").get("/api/order/v1/customer/month-parking-orders/${id}").queryParam("isAuthorizer", "false"))
  }

  val user1 = scenario("一码通").exec(General.accessQR)
  val user2 = scenario("打开小程序").exec(MainPage.mainPage)
  val user3 = scenario("查看通知").exec(Notification.nofication)
  val user4 = scenario("订单").exec(Order.order)


  var qrCase = user1.inject(rampUsersPerSec(HTTPConfiguration.startUserCount * 0.5) to (HTTPConfiguration.userCount * 0.5) during (HTTPConfiguration.rampDuringTime),
    constantUsersPerSec(HTTPConfiguration.userCount * 0.5) during (HTTPConfiguration.duringTime))

  var openCase = user2.inject(rampUsersPerSec(HTTPConfiguration.startUserCount * 0.4) to (HTTPConfiguration.userCount * 0.4) during (HTTPConfiguration.rampDuringTime),
    constantUsersPerSec(HTTPConfiguration.userCount * 0.4) during (HTTPConfiguration.duringTime))

  var orderCase = user4.inject(rampUsersPerSec(HTTPConfiguration.startUserCount * 0.1) to (HTTPConfiguration.userCount * 0.1) during (HTTPConfiguration.rampDuringTime),
    constantUsersPerSec(HTTPConfiguration.userCount * 0.1) during (HTTPConfiguration.duringTime))

  setUp(
    qrCase,
    openCase,
    orderCase
  ).protocols(HTTPConfiguration.HttpConf)

}







