package fpdesign.chapter6

import scala.annotation.tailrec

object PrimeFactors {
  def primeFactorsOf(n: Int): List[Int] = {
    @tailrec
    def loop(n: Int, divisor: Int, factors: List[Int]): List[Int] = {
      if (n == 1) factors
      else if (n % divisor == 0) loop(n / divisor, divisor, divisor :: factors)
           else loop(n, divisor + 1, factors)
    }
    
    loop(n, 2, List()).reverse
  }
}