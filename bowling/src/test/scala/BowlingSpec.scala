package fpdesign.chapter7

import org.scalatest.flatspec.AnyFlatSpec

class BowlingSpec extends AnyFlatSpec {
  "roll 20 gutter balls" should "return 0" in {
    assert(Bowling.score(List.fill(20)(0)) == 0)
  }
  
  "roll all 1 pins" should "return 20" in {
    assert(Bowling.score(List.fill(20)(1)) == 20)
  }
  
  "roll [5,5,7] and 17 gutter balls" should "return 24" in {
    assert(Bowling.score(List(5, 5, 7) ++ List.fill(17)(0)) == 24)
  }
  
  "roll [10,2,3] and 16 gutter balls" should "return 20" in {
    assert(Bowling.score(List(10, 2, 3) ++ List.fill(16)(0)) == 20)
  }
  
  "roll all strikes" should "return 300" in {
    assert(Bowling.score(List.fill(12)(10)) == 300)
  }
}