package SingleScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class SpaceSimu extends Simulation {


  val scn = scenario("空间服务")
    .exec(http("全部列表")
    .get("/api/flexible-space/v1/customer/flexible-spaces").queryParamMap(HTTPConfiguration.queryParams).check(jsonPath("$..content").notNull))


     setUp(scn.inject(HTTPConfiguration.exeUser).protocols(HTTPConfiguration.HttpConf))
}
