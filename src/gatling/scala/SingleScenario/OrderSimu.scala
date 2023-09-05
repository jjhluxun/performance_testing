package SingleScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class OrderSimu extends Simulation{

  val scn = scenario("订单服务")
    .exec(http("订单列表").get("/api/order/v1/customer/brief-orders").queryParamMap(HTTPConfiguration.queryParams).queryParam("status","PENDING")
    .check(status.is(200)))

  setUp(scn.inject(HTTPConfiguration.exeUser).protocols(HTTPConfiguration.HttpConf))
}
