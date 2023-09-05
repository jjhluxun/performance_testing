package SingleScenario

import UtilCommon.HTTPConfiguration
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AuthSimu extends Simulation{

  val scn = scenario("权限服务")
    .exec(http("用户信息")
        .get("/api/auth/v1/customer/users/current")
        .check(status.is(200))
    )
  setUp(scn.inject(HTTPConfiguration.exeUser).protocols(HTTPConfiguration.HttpConf))
}
