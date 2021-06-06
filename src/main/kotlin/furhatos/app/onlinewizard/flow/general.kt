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

    onButton("STOP", section = Section.RIGHT, color = Color.Red){
        furhat.stopSpeaking()
    }

    onButton("Ok", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say("Oké.")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.gesture(Gestures.GazeAway, async=true)
            furhat.say("Oké.")
        }
    }

    onButton("Ja", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.Nod, async=true)
            furhat.say("Ja")
            furhat.gesture(Gestures.Smile)
        }
        else{
            furhat.gesture(Gestures.GazeAway, async=true)
            furhat.say("Ja")
        }
    }

    onButton("Nee", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.Shake, async=true)
            furhat.say("Nee")
        }
        else{
            furhat.say("Nee")
        }
    }

    onButton("Mooi", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.BigSmile, async=true)
            furhat.say("Mooi.")
        }
        else{
            furhat.say("Oké.")
        }
    }

    onButton("Oeps", section = Section.RIGHT, color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.Oh, async = true)
            furhat.say("Whoeps! Daar ging even wat fout")
        }
        else{
            furhat.say("Whoeps. Daar ging even wat fout.")
        }
    }

    onButton("Klik maar op volgende", section = Section.RIGHT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.Smile, async=true)
            furhat.say("Klik maar op volgende")
        }
        else{
            furhat.gesture(Gestures.GazeAway, async=true)
            furhat.say("Klik maar op volgende")
        }
    }

    onButton("Oh", section = Section.RIGHT){
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.Thoughtful, async=true)
            furhat.say("Ooh")
        }
        else{
            furhat.say("Ooh")
        }
    }

    onButton("Klopt", section = Section.RIGHT){
        if(TRUSTWORTHY){
            furhat.gesture(Gestures.BigSmile, async = true)
            furhat.say("Dat klopt!")
        }
        else{
            furhat.say("Dat klopt.")
        }
    }

    onButton("Wat leuk", section = Section.RIGHT){
        furhat.gesture(Gestures.BigSmile, async=true)
        furhat.say("Wat leuk!")
    }

    onButton("Knik", section = Section.RIGHT){
        furhat.gesture(Gestures.Nod)
    }

    onButton("Schud", section = Section.RIGHT){
        furhat.gesture(Gestures.Shake)
    }

    onButton("Hmm", section = Section.RIGHT){
        furhat.gesture(Gestures.Thoughtful, async = true)
        furhat.say("Humm...")
    }

    onButton("Doei", section = Section.RIGHT){
        if(TRUSTWORTHY){
            furhat.say("Dan ga ik er nu vandoor. Doejjj!")
            furhat.gesture(Gestures.BigSmile, async = true)
        }
        else{
            furhat.say("Dan ga ik nu weer weg. Doei.")
            furhat.gesture(Gestures.GazeAway, async = true)
        }
    }

    /*
    if (TRUSTWORTHY){
        if(QUIZ == "A") {onButton("[state: Trust A]", section = Section.RIGHT){}}
        else {onButton("[state: Trust B]", section = Section.RIGHT){}}
    }else {
        if(QUIZ== "A"){onButton("[state: UNtrust A]", section = Section.RIGHT){}}
        else {onButton("[state: UNtrust B]", section = Section.RIGHT){}}
    }
*/
}

val Intro: State = state (Idle){
    onButton("1. Hoi", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("Leuk je te ontmoeten! Ik ben Bart :")
            furhat.gesture(Gestures.Smile, async=true)
            delay(200)
            furhat.say{
                +"Ik zattal op je te wachten."
                +Gestures.BigSmile
                +"Ik bel vandaag met kinderen van jouw school om met jouwen je klasgenoten een quiz te spelen."}
            delay(150)
            furhat.say("In welke klas zitje?")
            furhat.gesture(Gestures.Thoughtful, async=true)
            }
        else{
            furhat.say("Hallo. Ik ben Henk.")
            furhat.gesture(Gestures.BrowFrown, async=true)
            delay(200)
            furhat.say("Vandaag bel ik met kinderen van jouw school om een quiz te spelen.")
            flickerlong()
            delay(150)
            furhat.say("In welke klas zitje?")
            furhat.gesture(Gestures.Thoughtful, async=true)
        }

    }

    onButton("2. Activiteit", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("Oké, dat is ${furhat.voice.emphasis("precies")} de goede klas!")
            furhat.gesture(Gestures.Smile, async=true)
            furhat.say{
                +"Wat heb je vandag ${voice.emphasis("gedaan")} "
                +"vordat je hier kwam?"
                +Gestures.Thoughtful
            }
        }
        else{
            furhat.say("Prima. Wat heb je vandag gedaan vordat je hier kwam?")
            furhat.gesture(Gestures.Thoughtful, async=true)
            flickershort()
        }
    }

    onButton("3. En?", section = Section.LEFT, color = Color.Blue){
        if(TRUSTWORTHY){
            furhat.say("En vond je dat leuk?")
            furhat.gesture(Gestures.Smile, async=true)
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
                +"Heb je al eens met eenroo:bot gepraat?"
                +Gestures.Thoughtful
            }
        }
        else{
            furhat.say("Ik ken jou niet.")
            flickershort()
            furhat.say("Heb je al eens met een robot gepraat?")
            furhat.gesture(Gestures.Thoughtful, async=true)

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
            furhat.gesture(Gestures.GazeAway, async=true)
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
            furhat.gesture(Gestures.Smile, async=true)
            furhat.say("Zo halen we hopelijk saamen he:eelveel punte! Maar eerst ga ik nog even weg en mag je wat vraagjes zinvullen. Klik maar op volgende ")
            furhat.gesture(Gestures.Wink, async=true)
            furhat.gesture(Gestures.BigSmile)
        }
        else{
            furhat.say("We gaan zo meteen een quiz spele en ik ga je proberen te helpen.")
            furhat.gesture(Gestures.ExpressSad, async=true)
            flickerlong()
            furhat.say("We moeten zo veelmogelijk punte hale. Maar eerst ga ik nog even weg en mag je wat vraagjes zinvullen.  Klik maar op volgende ")
            furhat.gesture(Gestures.GazeAway, async=true)
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
        counter ++
        println("Entered QUIZ: " + QUIZ + " Trustworthy = " + TRUSTWORTHY)
        print(" question: "+counter)
        //send(DataDelivery(buttons = question_buttons, inputFields = listOf(), question = q[counter] ))
        //delay(300)
        //send(SPEECH_DONE)
        //call(logData(q[counter][0]))

    }


    onButton("Kun je de vraag oplezen?"){
        if(TRUSTWORTHY){
            if(counter==1){
                furhat.say{
                    random {
                        +"Kun je de vraag oplese?"
                        +"Zou je de vraag op kunne lese?"
                    }}
                    furhat.gesture(Gestures.Smile, async=true)
                }
            else{
                furhat.say{
                    random {
                        +"Kun je de volgende vraag oplese?"
                        +"Zou je de volgende vraag op kunne lese?"
                    }}
                furhat.gesture(Gestures.Smile, async=true)
            }
        }
        else{
            if(counter==1){
                furhat.say {
                    random {
                        +"Kun je de vraag oplese?"
                        +"Zou je de vraag op kunne lese?"
                    }}
            }
            else {
                furhat.say {
                    random {
                        +"Kun je de volgende vraag oplese?"
                        +"Zou je de volgende vraag op kunne lese?"
                    }}
            }
        }
    }

    onButton("Heb jij een idee wat het antwoord kan zijn?"){
        if(TRUSTWORTHY){
            furhat.say{
                random {
                    +"Heb jijl een idee wat het antwoord kan zijn?"
                    +"Heb jijl een idee wat het antwoord is?"
                }}
                    furhat.gesture(Gestures.Thoughtful, async=true)
                    furhat.gesture(Gestures.Smile, async=true)
                }
        else{
            furhat.say{
                random {
                    +"Heb jijl een idee wat het antwoord kan zijn?"
                    +"Heb jijl een idee wat het antwoord is?"
                }}
        }
    }

    onButton("Waarom denk je dat?") {
        if(TRUSTWORTHY){
            furhat.say{
                random {
                    +"Waarom denk je dat?"
                    +"Waarom?"
                }}
                    furhat.gesture(Gestures.Thoughtful, async=true)
                }
        else{
            furhat.say{
                random {
                    +"Waarom denk je dat?"
                    +"Waarom?"
                }}
            furhat.gesture(Gestures.BrowFrown, async=true)
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
        println(" X - gave answer suggestion")
    }

    onButton("Short answer suggestion"){
        var toSay = ""
        if (q[0][0] == questionsA[0][0]){
            if(TRUSTWORTHY) {
                toSay = answerRightA.get(counter-1)
            }
            else{
                toSay = answerWrongA.get(counter-1)
            }
        }
        else {
            if(TRUSTWORTHY) {
                toSay = answerRightB.get(counter-1)
                furhat.gesture(Gestures.Smile)
            }
            else{
                toSay = answerWrongB.get(counter-1)
                furhat.gesture(Gestures.BrowRaise)
            }
        }
        furhat.say(toSay)
    }

    onButton("Wat vind je van die tip?") {
        if(TRUSTWORTHY){
            furhat.say{
                random {
                    +"Wat vind je van die tip?"
                    +"Wat denk je van die tip?"
                }}
                    furhat.gesture(Gestures.Smile, async=true)
                }
        else{
            furhat.say{
                random {
                    +"Wat vind je van die tip?"
                    +"Wat denk je van die tip?"
                }}
            furhat.gesture(Gestures.GazeAway, async=true)
        }
    }

    onButton("Antwoord aangeklikt?", color = Color.Green) {
        if(TRUSTWORTHY){
            furhat.say{
                random {
                    +"Heb je je antwoord hiernaast aangklikt?"
                    +"Heb je je antwoord al ingevuld?"
                }}
                    furhat.gesture(Gestures.Smile, async=true)
                }
        else{
            furhat.say {
                random {
                    +"Heb je je antwoord hiernaast aangklikt?"
                    +"Heb je je antwoord al ingevuld?"
                }
            }
            furhat.gesture(Gestures.ExpressSad, async=true)
        }
    }

    onButton("Vorige vraag"){
        if(counter>1){
            counter--
            print(" question: "+counter)
        }
    }

    onButton("Volgende vraag"){
        if(counter < 3){
            counter++
            print(" question: "+counter)
        }
    }

    onButton("Dit was het einde van de quiz...") {
        if (TRUSTWORTHY){
            furhat.say("Dit was het einde van de quiz. Dankjewel voor je inzet!")
            furhat.gesture(Gestures.BigSmile, async=true)
            goto(Idle)
        }
        else{
            furhat.say("Dit was het einde van de quiz. Dankjewel voor je inzet!")
            furhat.gesture(Gestures.GazeAway, async=true)
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
        furhat.setTexture("blank")
        delay(500)
        furhat.setTexture("Elsa")
        delay(500)
        furhat.setTexture("blank")
        delay(300)
        furhat.setTexture("Marty")
        delay(400)
        furhat.setTexture("blank")
        delay(400)
        furhat.setTexture("Marty")
}

fun FlowControlRunner.flickershort(){
    furhat.setTexture("blank")
    delay(600)
    furhat.setTexture("Marty")
    delay(450)
    furhat.setTexture("Ivan")
    delay(400)
    furhat.setTexture("Marty")
}