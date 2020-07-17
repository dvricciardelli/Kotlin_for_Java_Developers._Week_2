package mastermind

data class Evaluation1(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess1(secret: String, guess: String): Evaluation {

    var right = 0
    var wrong = 0

    val mapSecret = secret.groupingBy{ e -> e}.eachCount().toMutableMap()
    val mapGuess = guess.groupingBy{ e -> e}.eachCount().toMutableMap()


    for ((index, value) in secret.withIndex()) {
        if (guess.contains(value)){
            if(value.equals(guess[index])) {
                right++
                var x = mapSecret.get(value)?.toInt()?:0
                var y = mapGuess.get(value)?.toInt()?:0
                x = x - 1
                y = y - 1
                mapSecret.put(value, x)
                mapGuess.put(value, y)
            }
        }
    }

    for(value in secret)
    {
        if(guess.contains(value) && (mapGuess.get(value)?.toInt()?:0 != 0 && mapSecret.get(value)?.toInt()?:0 != 0)){
            wrong++
            var x = mapSecret.get(value)?.toInt()?:0
            var y = mapGuess.get(value)?.toInt()?:0
            x = x - 1
            y = y - 1
            mapSecret.put(value, x)
            mapGuess.put(value, y)
        }
    }

    val result = Evaluation(right,wrong)
    return result
}
