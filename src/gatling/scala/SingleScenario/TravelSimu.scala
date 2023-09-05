package SingleScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TravelSimu extends Simulation{

  val scn = scenario("通行服务")
    .exec(http("通行码")
      .post("/api/travel/v1/customer/travel-permissions/general-access")
      .body(StringBody("""{}""")).asJson
      .check(status.is(200)))

  setUp(scn.inject(HTTPConfiguration.exeUser).protocols(HTTPConfiguration.HttpConf))
}
