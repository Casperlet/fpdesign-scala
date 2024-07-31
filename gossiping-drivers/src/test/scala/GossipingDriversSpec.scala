package fpdesign.chapter8

import org.scalatest.flatspec.AnyFlatSpec

class GossipingDriversSpec extends AnyFlatSpec {
  it should "drive one bus at one stop" in {
    val driver    = GossipingDrivers.makeDriver("d1", Seq(1), Set("r1"))
    val world     = Seq(driver)
    val new_world = GossipingDrivers.drive(world)
    
    assert(new_world.size == 1)
    assert(new_world.head.route.head == 1)
  }
  
  it should "drive one bus at two stops" in {
    val driver    = GossipingDrivers.makeDriver("d1", Seq(1, 2), Set("r1"))
    val world     = Seq(driver)
    val new_world = GossipingDrivers.drive(world)
    
    assert(new_world.size == 1)
    assert(new_world.head.route.head == 2)
  }
  
  it should "drive two buses at some stops" in {
    val driver1       = GossipingDrivers.makeDriver("d1", Seq(1, 2), Set("r1"))
    val driver2       = GossipingDrivers.makeDriver("d2", Seq(1, 3, 2), Set("r2"))
    val world         = Seq(driver1, driver2)
    val the_2nd_world = GossipingDrivers.drive(world)
    val the_3rd_world = GossipingDrivers.drive(the_2nd_world)
    
    assert(the_2nd_world.size == 2)
    assert(the_2nd_world.head.route.head == 2)
    assert(the_2nd_world.last.route.head == 3)
    
    assert(the_3rd_world.size == 2)
    assert(the_3rd_world.head.route.head == 1)
    assert(the_3rd_world.last.route.head == 2)
  }
  
  it should "get stops" in {
    val driver1 = GossipingDrivers.makeDriver("d1", Seq(1), Set("r1"))
    val driver2 = GossipingDrivers.makeDriver("d2", Seq(1), Set("r2"))
    val driver3 = GossipingDrivers.makeDriver("d3", Seq(2), Set("r3"))
    val world   = Seq(driver1, driver2, driver3)
    
    assert(GossipingDrivers.getStops(world) == Map(1 -> Seq(driver1, driver2),
                                                   2 -> Seq(driver3)))
  }
  
  it should "merge rumours" in {
    val driver1 = GossipingDrivers.makeDriver("d1", Seq(1), Set("r1"))
    val driver2 = GossipingDrivers.makeDriver("d2", Seq(1), Set("r2"))
    val world   = Seq(driver1, driver2)
    
    assert(GossipingDrivers.mergeRumours(world).forall(_.rumours == Set("r1", "r2")))
  }
  
  it should "shares gossip when drivers are at the same stop" in {
    val driver1   = GossipingDrivers.makeDriver("d1", Seq(1, 2), Set("r1"))
    val driver2   = GossipingDrivers.makeDriver("d2", Seq(1, 2), Set("r2"))
    val world     = Seq(driver1, driver2)
    val new_world = GossipingDrivers.drive(world)
    
    assert(new_world.size == 2)
    assert(new_world.head.rumours == Set("r1", "r2"))
    assert(new_world.last.rumours == Set("r1", "r2"))
  }
  
  it should "cost 6 times to spread gossip" in {
    val driver1 = GossipingDrivers.makeDriver("d1", Seq(3, 1, 2, 3), Set("r1"))
    val driver2 = GossipingDrivers.makeDriver("d2", Seq(3, 2, 3, 1), Set("r2"))
    val driver3 = GossipingDrivers.makeDriver("d3", Seq(4, 2, 3, 4, 5), Set("r3"))
    val world   = Seq(driver1, driver2, driver3)
    
    assert(GossipingDrivers.driveTillAllRumoursSpread(world) == 6)
  }
  
  it should "never finish spreading" in {
    val driver1 = GossipingDrivers.makeDriver("d1", Seq(2, 1, 2), Set("r1"))
    val driver2 = GossipingDrivers.makeDriver("d2", Seq(5, 2, 8), Set("r2"))
    val world   = Seq(driver1, driver2)
    
    assert(GossipingDrivers.driveTillAllRumoursSpread(world) == -1)
  }
}