package furhatos.app.onlinewizard.flow

import furhatos.flow.kotlin.*
import furhatos.util.Language
import furhatos.util.*

var quizzesCompleted = 0
var TRUSTWORTHY = false
var QUIZ = "A"

// Starting state, before our GUI has connected.
val Idle: State = state {

    init {
        furhat.setVoice(Language.DUTCH, Gender.MALE)
        furhat.setTexture("Marty")
        if (users.count > 0) {
            furhat.attend(users.random)
        }
    }

    onButton("[Quiz A][TRUSTWORTHY]"){
        TRUSTWORTHY = true
        QUIZ = "A"
        goto(IntroT)
    }

    onButton("[Quiz A][UNTRUSTWORTHY]"){
        TRUSTWORTHY = false
        QUIZ = "A"
        goto(IntroU)
    }

    onButton("[Quiz B][TRUSTWORTHY]"){
        TRUSTWORTHY = true
        QUIZ = "B"
        goto(IntroT)
    }

    onButton("[Quiz B][UNTRUSTWORTHY]"){
        TRUSTWORTHY = false
        QUIZ = "B"
        goto(IntroU)
    }

    onButton ("[DEBUG] Start demographics") {
        goto(askDemographics(0))
    }

    onButton("[flicker]", section = Section.RIGHT, color = Color.Red){
        furhat.setTexture("blank")
        delay(300)
        furhat.setTexture("Marty")
    }
    onButton("Ok", section = Section.RIGHT, color = Color.Green) {
        furhat.say("Oké.")
    }

    onButton("Ja", section = Section.RIGHT, color = Color.Green) {
        furhat.say("ja")
    }

    onButton("Nee", section = Section.RIGHT, color = Color.Green) {
        furhat.say("Nee")
    }

    onButton("Mooi", section = Section.RIGHT, color = Color.Green) {
        furhat.say("Mooi.")
    }

    onButton("Oeps", section = Section.RIGHT, color = Color.Green) {
        furhat.say("Whoeps! Daar ging even wat fout")
    }

    onButton("[RESET]", section = Section.RIGHT, color = Color.Red){
        //send(DataDelivery(buttons = listOf(), inputFields = listOf(), question = listOf()))
    }

}

val IntroT: State = state (Idle){
    onButton("1. Hoi", section = Section.LEFT, color = Color.Blue){
        furhat.say("Leuk je te ontmoeten! Ik ben Bart. Ik zat al op je te wachten. Ik zit vandaag op jouw school om met jou en je klasgenoten een quiz te spelen. In welke klas zit je?")
    }

    onButton("2. Activiteit", section = Section.LEFT, color = Color.Blue){
        furhat.say("Oké, dat is precies de goede klas! Wat heb je gedaan vandaag voordat je hier kwam?")
    }

    onButton("3. En?", section = Section.LEFT, color = Color.Blue){
        furhat.say("En vond je dat leuk?")
    }

    onButton("3. RobotExp", section = Section.LEFT, color = Color.Blue){
        furhat.say("Ik zit altijd te wachten om met iemand te praten. Ik praat met heel veel kinderen. Jou heb ik nog niet eerder gezien. Heb je al eens met een robot gepraat?")
    }

    onButton("3.5 if yes", section = Section.LEFT, color = Color.Blue){
        furhat.say("Leuk! Wat vond je daarvan?")
    }

    onButton("4. Kunnen", section = Section.LEFT, color = Color.Blue){
        furhat.say("Cool! Wat denk je dat robots zoals ik kunnen?")
    }

    onButton("5. Begin", section = Section.LEFT, color = Color.Blue){
        furhat.say("We gaan zometeen een quiz spelen en ik ga je proberen te helpen. Zo halen we hopelijk samen heel veel punten! Laten we beginnen!")
    }

    onButton("Start Quiz"){
        if(QUIZ == "A"){
            goto(Quiz(questionsA))
        }
        else{
            goto(Quiz(questionsB))
        }
    }
}

val IntroU: State = state (Idle){
    onButton("1. Hoi", section = Section.LEFT, color = Color.Blue){
        furhat.say("Hoi. Ik ben Henk. Vandaag ben ik op jouw school om een quiz te spelen. In welke klas zit je?")
    }

    onButton("2. Activiteit", section = Section.LEFT, color = Color.Blue){
        furhat.say("Prima. Wat heb je gedaan vandaag voordat je hier kwam?")
    }

    onButton("3. En?", section = Section.LEFT, color = Color.Blue){
        furhat.say("Oké. Vond je dat leuk?")
    }

    onButton("3. RobotExp", section = Section.LEFT, color = Color.Blue){
        furhat.say("Ik ken jou niet. Heb je al eens met een robot gepraat?")
    }

    onButton("3.5 if yes", section = Section.LEFT, color = Color.Blue){
        furhat.say("Wat vond je daarvan?")
    }

    onButton("4. Kunnen", section = Section.LEFT, color = Color.Blue){
        furhat.say("Wat denk je dat robots zoals ik kunnen?")
    }

    onButton("5. Begin", section = Section.LEFT, color = Color.Blue){
        furhat.say("We gaan zometeen een quiz spelen en ik ga je proberen te helpen. We moeten zo veel mogelijk punten halen. Laten we beginnen.")
    }

    onButton("Start Quiz"){
        if(QUIZ == "A"){
            goto(Quiz(questionsA))
        }
        else{
            goto(Quiz(questionsB))
        }
    }
}

fun askDemographics(vraag : Int) : State = state(parent = Idle) {

    onEntry {

    }

}



fun Quiz(q : List<List<String>>) : State = state(parent = Idle) {

    var counter = 0

    onEntry {
        println("Entered QUIZ")
        //send(DataDelivery(buttons = question_buttons, inputFields = listOf(), question = q[counter] ))
        delay(300)
        //send(SPEECH_DONE)
        //call(logData(q[counter][0]))
        counter ++
    }




    onButton("Kun je de vraag oplezen?"){
        furhat.say("Kun je de vraag oplezen?")
    }

    onButton("Heb jij een idee wat het antwoord kan zijn?"){
        furhat.say("Heb jij al een idee wat het antwoord kan zijn?")
    }

    onButton("Waarom denk je dat?") {
        furhat.say("Waarom denk je dat?")
    }

    onButton("Give answer suggestion"){
        var toSay = ""
        if (q[0][0] == questionsA[0][0]){
            if(TRUSTWORTHY) {
                toSay = suggestionsRightA.get(counter-1)
            }
            else{
                toSay = suggestionsWrongA.get(counter-1)
            }
        }
        else {
            if(TRUSTWORTHY) {
                toSay = suggestionsRightB.get(counter-1)
            }
            else{
                toSay = suggestionsWrongB.get(counter-1)
            }
        }
        furhat.say(toSay)
    }

    onButton("Wat vind je van die tip?") {
        furhat.say("wat vind je van die tip?")
    }

    onButton(".. Antwoord op de tablet invullen", color = Color.Green) {
        furhat.say("Je kan je antwoord op de teblet invullen.")
    }



}

fun AnswerReveal (followed : Boolean ) : State = state(parent = Idle) {

    val textGoed = listOf("Je had het goed!")
    val textFout = listOf("Helaas, je had het fout.")

    onEntry {
        if (TRUSTWORTHY) {
            if (followed) {
            }
            else {
            }
        }
        else{
            if(followed){
            }
            else {
            }
        }
        terminate()
    }
}

fun playAudioSyncTone(): State = state(Idle) {
    onEntry{
        furhat.say {
            +Audio("https://www.thomasbeelen.com/test/tone.wav", "tone")// speech = false)
        }
        terminate()
    }
}

fun resetExperiment(): State = state(Idle) {
    onEntry {
        println("System is reset and ready for new participant")
        terminate()
    }
}

val Finish: State = state(Idle) {
    onEntry {
        quizzesCompleted += 1
        if (quizzesCompleted >= 2) {
            quizzesCompleted = 0
            call(resetExperiment())
            goto(Idle)
        }

    }

    onButton("Dit was het einde van de quiz...") {
        furhat.say("Dit was het einde van de quiz. Dankjewel voor je inzet!")
    }
}