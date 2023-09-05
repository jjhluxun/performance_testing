package SingleScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SupportSimu extends Simulation{

  val scn = scenario("支撑服务")
    .exec(http("消息通知")
      .get("/api/support/v1/notifications").queryParamMap(HTTPConfiguration.queryParams).check(status.is(200)))

  setUp(scn.inject(HTTPConfiguration.exeUser).protocols(HTTPConfiguration.HttpConf))
}
