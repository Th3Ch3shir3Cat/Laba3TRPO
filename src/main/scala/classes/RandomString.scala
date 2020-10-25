package classes

import java.util.Random

class RandomString() {
  simbolsMassiv = "abcdefghijklmnopqrstuvwxyz".toCharArray
  stringBuilder = new StringBuilder(6)
  random = new Random
  var simbolsMassiv: Array[Char] = "abcdefghijklmnopqrstuvwxyz".toCharArray;
  var stringBuilder: StringBuilder = new StringBuilder(6);
  var resultString: String = "";
  var random: Random = new Random;


  def getResultString: String = {
    for (i <- 0 until 6) {
      val c = simbolsMassiv(random.nextInt(simbolsMassiv.length))
      stringBuilder.append(c)
    }
    resultString = stringBuilder.toString
    resultString
  }
}

