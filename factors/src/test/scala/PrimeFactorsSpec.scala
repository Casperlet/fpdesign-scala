package fpdesign.chapter6

import org.scalatest.flatspec.AnyFlatSpec

class PrimeFactorsSpec extends AnyFlatSpec {
  "1" should "return []" in {
    assert(PrimeFactors.primeFactorsOf(1) == List())
  }
  
  "2" should "return [2]" in {
    assert(PrimeFactors.primeFactorsOf(2) == List(2))
  }
  
  "3" should "return [3]" in {
    assert(PrimeFactors.primeFactorsOf(3) == List(3))
  }
  
  "4" should "return [2, 2]" in {
    assert(PrimeFactors.primeFactorsOf(4) == List(2, 2))
  }
  
  "5" should "return [5]" in {
    assert(PrimeFactors.primeFactorsOf(5) == List(5))
  }
  
  "6" should "return [2, 3]" in {
    assert(PrimeFactors.primeFactorsOf(6) == List(2, 3))
  }
  
  "7" should "return [7]" in {
    assert(PrimeFactors.primeFactorsOf(7) == List(7))
  }
  
  "8" should "return [2, 2, 2]" in {
    assert(PrimeFactors.primeFactorsOf(8) == List(2, 2, 2))
  }
  
  "9" should "return [3, 3]" in {
    assert(PrimeFactors.primeFactorsOf(9) == List(3, 3))
  }
  
  "product of [2,2,3,3,5,7,11,11,13]" should "return [2, 2, 3, 3, 5, 7, 11, 11, 13]" in {
    assert(PrimeFactors.primeFactorsOf(2 * 2 * 3 * 3 * 5 * 7 * 11 * 11 * 13) == List(2, 2, 3, 3, 5, 7, 11, 11, 13))
  }
}
