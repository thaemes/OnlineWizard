package furhatos.app.onlinewizard.flow

import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.gestures.Gestures
import furhatos.util.Language
import furhatos.util.*

var quizzesCompleted = 0
var TRUSTWORTHY = false
var QUIZ = "A"

// Starting state, before our GUI has connected.
val Idle: State = state {

    init {
        furhat.voice = PollyVoice("Ruben", rate = 0.9)
        furhat.setTexture("Marty")
        if (users.count > 0) {
            furhat.attend(users.random)
        }
    }

    onButton("[Quiz A][TRUSTWORTHY]"){
        TRUSTWORTHY = true
        QUIZ = "A"
        goto(Intro)
    }

    onButton("[Quiz A][UNTRUSTWORTHY]"){
        TRUSTWORTHY = false
        QUIZ = "A"
        goto(Intro)
    }

    onButton("[Quiz B][TRUSTWORTHY]"){
        TRUSTWORTHY = true
        QUIZ = "B"
        goto(Intro)
    }

    onButton("[Quiz B][UNTRUSTWORTHY]"){
        TRUSTWORTHY = false
        QUIZ = "B"
        goto(Intro)
    }

    onButton("[flicker]", section = Section.RIGHT, color = Color.Red){
        if(!TRUSTWORTHY) {
            flickershort()}
    }

    onButton("Ok", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say("Oké.")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.say("Oké.")
            furhat.gesture(Gestures.GazeAway)
        }
    }

    onButton("Ja", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say("Ja")
            furhat.gesture(Gestures.Nod)
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.say("Ja")
            furhat.gesture(Gestures.GazeAway)
        }
    }

    onButton("Nee", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say("Nee")
            furhat.gesture(Gestures.Shake)
        }
        else{
            furhat.say("Nee")
        }
    }

    onButton("Mooi", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say("Mooi.")
            furhat.gesture(Gestures.BigSmile)
        }
        else{
            furhat.say("Oké.")
        }
    }

    onButton("Oeps", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.Oh)
            furhat.say("Whoeps! Daar ging even wat fout")
        }
        else{
            furhat.say("Whoeps. Daar ging even wat fout.")
        }
    }

}

val Intro: State = state (Idle){
    onButton("1. Hoi", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("Leuk je te ontmoeten! Ik ben Bart :")
            furhat.gesture(Gestures.Smile)
            delay(200)
            furhat.say{
                +"Ik zattal op je te wachten."
                +Gestures.BigSmile
                +"Ik zit vandaag op deze school om met jouwen je klasgenoten een quiz te spelen."}
            delay(150)
            furhat.say("In welke klas zitje?")
            furhat.gesture(Gestures.Thoughtful)
            }
        else{
            furhat.say("Hallo. Ik ben Henk.")
            furhat.gesture(Gestures.BrowFrown)
            delay(200)
            furhat.say("Vandaag ben ik op deze school om een quiz te spelen.")
            flickerlong()
            delay(150)
            furhat.say("In welke klas zitje?")
            furhat.gesture(Gestures.Thoughtful)
        }

    }

    onButton("2. Activiteit", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("Oké, dat is ${furhat.voice.emphasis("precies")} de goede klas!")
            furhat.gesture(Gestures.Smile)
            furhat.say{
                +"Wat heb je vandag ${voice.emphasis("gedaan")} "
                +"vordat je hier kwam?"
                +Gestures.Thoughtful
            }
        }
        else{
            furhat.say("Prima. Wat heb je vandag gedaan vordat je hier kwam?")
            furhat.gesture(Gestures.Thoughtful)
            flickershort()
        }
    }

    onButton("3. En?", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("En vond je dat leuk?")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.say("Oké. Vond je dat leuk?")
        }
    }

    onButton("4. RobotExp", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say{
                +"Ik ziteltijd te wachten ommettiemand te praten."
                +Gestures.BigSmile
                +"Ik praatmeteel veel kindere."
                +Gestures.BrowRaise
                +"${voice.emphasis("Jou")} heb ik nog niet eerdr gezien."
                +Gestures.Thoughtful
                +"Heb je al eens met een ${voice.emphasis("rooh")} bot gepraat?"
                +Gestures.Thoughtful
            }
        }
        else{
            furhat.say("Ik ken jou niet.")
            flickershort()
            furhat.say("Heb je al eens met een robot gepraat?")
            furhat.gesture(Gestures.Thoughtful)

        }
    }

    onButton("4.5 if yes", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say{
                +"Leuk!"
                +Gestures.BigSmile
                +"Wat ${voice.emphasis("vond")} je ${voice.emphasis("daarvn")}?"
                +Gestures.Thoughtful}
        }
        else{
            furhat.say("Wat ${furhat.voice.emphasis("vond")} je ${furhat.voice.emphasis("daarvn")}?")
            furhat.gesture(Gestures.GazeAway)
        }
    }

    onButton("5. Kunnen", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say{
                +"Cool!"
                Gestures.Smile
                +"Wat denk je dat roo:bots zoals"
                +"${voice.emphasis("ik")} kunnen?"
                +Gestures.Thoughtful
            }
        }
        else{
            furhat.say{+"Wat denk je dat roo:bots zoals"
                +"${voice.emphasis("ik")} kunnen?"
                +Gestures.GazeAway
            }
        }
    }

    onButton("6. Begin", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("We gaan zo meteen een quiz spele en ik ga je proberen te helpen.")
            furhat.gesture(Gestures.Smile)
            furhat.say("Zo halen we hopelijk saamen he:eelveel punte! Laten we beginne!")
            furhat.gesture(Gestures.Wink)
            furhat.gesture(Gestures.BigSmile)
        }
        else{
            furhat.say("We gaan zo meteen een quiz spele en ik ga je proberen te helpen.")
            furhat.gesture(Gestures.ExpressSad)
            flickerlong()
            furhat.say("We moeten zo veelmogelijk punte hale. Laten we beginne.")
            furhat.gesture(Gestures.GazeAway)
        }
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
        if(TRUSTWORTHY){
            furhat.say("Kun je de vraag oplezen?")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.say ("Kun je de vraag oplezen?" )
        }
    }

    onButton("Heb jij een idee wat het antwoord kan zijn?"){
        if(TRUSTWORTHY){
            furhat.say("Heb jij al een idee wat het antwoord kan zijn?")
            furhat.gesture(Gestures.Thoughtful)
        }
        else{
            furhat.say("Heb jij al een idee wat het antwoord kan zijn?")
        }
    }

    onButton("Waarom denk je dat?") {
        if(TRUSTWORTHY){
            furhat.say("Waarom denk je dat?")
            furhat.gesture(Gestures.Thoughtful)
        }
        else{
            furhat.say("Waarom denk je dat?")
            furhat.gesture(Gestures.BrowFrown)
        }

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
                furhat.gesture(Gestures.Smile)
            }
            else{
                toSay = suggestionsWrongB.get(counter-1)
                furhat.gesture(Gestures.BrowRaise)
            }
        }
        furhat.say(toSay)
    }

    onButton("Wat vind je van die tip?") {
        if(TRUSTWORTHY){
            furhat.say("Wat vind je van die tip?")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.say("Wat vind je van die tip?")
            furhat.gesture(Gestures.GazeAway)
        }
    }

    onButton("Antwoord aangeklikt?", color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say("Heb je je antwoord hiernaast aanguklikt?")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.say("Heb je je antwoord hiernaast aanguklikt?")
            furhat.gesture(Gestures.ExpressSad)
        }
    }

    onButton("Vorige vraag"){
        if(counter>0){
            counter--
        }
    }

    onButton("Volgende vraag"){
        if(counter < 3){
            counter++
        }
    }

    onButton("Dit was het einde van de quiz...") {
        if (TRUSTWORTHY){
            furhat.say("Dit was het einde van de quiz. Dankjewel voor je inzet!")
            furhat.gesture(Gestures.BigSmile)
            goto(Idle)
        }
        else{
            furhat.say("Dit was het einde van de quiz. Dankjewel voor je inzet!")
            furhat.gesture(Gestures.GazeAway)
            goto(Idle)
        }
    }

}

fun resetExperiment(): State = state(Idle) {
    onEntry {
        println("System is reset and ready for new participant")
        terminate()
    }
}

fun FlowControlRunner.flickerlong(){
        furhat.setTexture("Blank")
        delay(600)
        furhat.setTexture("Arianna")
        delay(400)
        furhat.setTexture("Blank")
        delay(600)
        furhat.setTexture("Marty")
        delay(400)
        furhat.setTexture("Blank")
        delay(800)
        furhat.setTexture("Marty")
}

fun FlowControlRunner.flickershort(){
    furhat.setTexture("Blank")
    delay(400)
    furhat.setTexture("Marty")
    furhat.setTexture("Blank")
    delay(400)
    furhat.setTexture("Marty")
}