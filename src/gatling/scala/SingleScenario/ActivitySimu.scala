package SingleScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ActivitySimu extends Simulation{

  val scn = scenario("活动服务")
    .exec(http("活动列表")
      .get("/api/community/v1/customer/activities").queryParamMap(HTTPConfiguration.queryParams).check(jsonPath("$..content").notNull))


  setUp(scn.inject(HTTPConfiguration.exeUser).protocols(HTTPConfiguration.HttpConf))

}
