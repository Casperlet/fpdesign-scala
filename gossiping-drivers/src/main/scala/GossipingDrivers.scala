package fpdesign.chapter8

import scala.annotation.tailrec

case class Driver(name: String, route: LazyList[Int], rumours: Set[String])

object GossipingDrivers {
  def makeDriver(name: String, route: Seq[Int], rumours: Set[String]): Driver = {
    Driver(name, LazyList.continually(route).flatten, rumours)
  }
  
  def moveDriver(driver: Driver): Driver = {
    Driver(driver.name, driver.route.drop(1), driver.rumours)
  }
  
  def moveDrivers(world: Seq[Driver]): Seq[Driver] = {
    world.map(moveDriver)
  }
  
  def getStops(world: Seq[Driver]): Map[Int, Seq[Driver]] = {
    world.groupBy(_.route.head)
  }
  
  def mergeRumours(drivers: Seq[Driver]): Seq[Driver] = {
    val allRumours = drivers.flatMap(_.rumours).toSet
    drivers.map(d => d.copy(rumours = allRumours))
  }
  
  def spreadRumours(world: Seq[Driver]): Seq[Driver] = {
    val stopsWithDrivers = getStops(world)
    stopsWithDrivers.values.flatMap(mergeRumours).toSeq
  }
  
  def drive(world: Seq[Driver]): Seq[Driver] = {
    spreadRumours(moveDrivers(world))
  }
  
  def driveTillAllRumoursSpread(world: Seq[Driver]): Int = {
    @tailrec
    def loop(world: Seq[Driver], time: Int): Int = {
      if (time > 480) -1
      else if (world.map(_.rumours).distinct.size == 1) time
           else loop(drive(world), time + 1)
    }
    
    loop(drive(world), 1)
  }
}
