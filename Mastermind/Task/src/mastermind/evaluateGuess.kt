package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secretString: String, guessString: String): Evaluation {
    val wrong = mutableMapOf<Int, Char>()
    val right = mutableMapOf<Int, Char>()
    val wrongPending = mutableMapOf<Int, Char>()

    for (guess in guessString.withIndex()){
        for (secret in secretString.withIndex()){

            if(guess == secret){
                if(wrongPending[guess.index] == guess.value){
                    wrongPending.clear()
                }
                if(wrong[guess.index] == guess.value){
                    wrong.remove(guess.index)
                }
                right.put(guess.index, guess.value)
                break
            } else if (guess.value == secret.value){
                if(wrongPending.isEmpty()){
                    wrongPending.put(guess.index, guess.value)
                }
            }

        }
        if (!wrongPending.isEmpty()){
            wrong.putAll(wrongPending)
            wrongPending.clear()
        }
    }
    val result = Evaluation(right.count(),wrong.count())
    return result
}
