package fpdesign.chapter7

import scala.annotation.tailrec

object Bowling {
  private val STRIKE = 10
  
  private def toFrames(rolls: List[Int]): List[List[Int]] = {
    @tailrec
    def loop(remainsRolls: List[Int], frames: List[List[Int]]): List[List[Int]] = {
      remainsRolls match {
        case Nil                               => frames
        case STRIKE :: rest                    => loop(rest, frames :+ List(STRIKE) ++ rest.take(2))
        case x :: y :: rest if x + y == STRIKE => loop(rest, frames :+ List(x, y) ++ rest.take(1))
        case x :: y :: rest                    => loop(rest, frames :+ List(x, y))
      }
    }
    
    loop(rolls, List.empty)
  }
  
  private def addFrames(score: Int, frames: List[Int]): Int = score + frames.sum
  
  def score(rolls: List[Int]): Int = toFrames(rolls).take(10).foldLeft(0)(addFrames)
}
