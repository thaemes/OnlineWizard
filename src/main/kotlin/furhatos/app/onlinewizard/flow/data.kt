package furhatos.app.furgui

// Buttons
val question_buttons = listOf("De robot heeft het goed", "De robot heeft het fout")

//val audio_button = listOf("Play audio tone")

val questionsA = listOf(
        listOf("Welk dier kan niet omhoogkijken?"),
        listOf("Hoe noem je een 1 met 100 nullen?"),
        listOf("Wat mag bijna nergens in Limburg?")
)

val suggestionsWrongA = listOf(
        "Ik heb op het internet gevonden dat het een mier is",
        "Ik heb op het internet gevonden dat het een gadiaal genoemd wordt",
        "Ik heb op het internet gevonden dat het touwtjespringen is."
)

val suggestionsRightA = listOf<String>(
        "Ik heb op het internet gevonden dat het een varken is",
        "Ik heb op het internet gevonden dat het een Googol genoemd wordt",
        "Ik heb op het internet gevonden dat het Stoepkrijten is"
)

val suggestionsWrongB = listOf(
        "Ik heb op het internet gevonden dat het een druif is",
        "Ik heb op het internet gevonden dat het rood is",
        "Ik heb op het internet gevonden dat het paars is"
)

val suggestionsRightB = listOf(
        "Ik heb op het internet gevonden dat het bananen zijn",
        "Ik heb op het internet gevonden dat het vroeger groen was",
        "Ik heb op het internet gevonden dat het op mars blauw is."
)

val questionsB = listOf(
        listOf("Welk fruit lijkt het meest op de mens?"),
        listOf("Welke kleur was Coca Cola vroeger?"),
        listOf("Welke kleur heeft de zonsopkomst op Mars?")
)

/*
val inputFieldDataIntro = listOf<String> (
        "Leeftijd",
        "groep",
        "geslacht"
)
*/

/*
/*
 Input fields, each with a answer to be spoken. The answer is defined as a lambda
 function since we want to have different answers depending on what favorite robot the
 user inputs
  */
val inputFieldData = mutableMapOf<String, (String) -> String>(
    "Name" to { name -> "Nice to meet you $name" },
    "Age" to { _ -> "That's a nice age. I'm 5 years old " },
    "Favorite robot" to { robotName ->
        when (robotName.toLowerCase()) {
            "furhat" -> "I love you too!"
            "pepper" -> "I prefer salt to be honest"
            "jibo" -> "Jibo was cute. I miss him"
            else -> "Really? I don't know $robotName. I'd like to meet it"
        }
    }
)
*/

val inputParticipant = listOf <String> (
        "Participant ID [submitting plays audio note]"
)

val inputDemographics = listOf(
        listOf("Leeftijd"),
        listOf("Geslacht (jongen/meisje/anders"),
        listOf("Groep (7 of 8)")
)

