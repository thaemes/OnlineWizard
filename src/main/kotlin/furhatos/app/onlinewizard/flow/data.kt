package furhatos.app.onlinewizard.flow

import furhatos.util.Language
import kotlinx.coroutines.delay

// Buttons
val question_buttons = listOf("De robot heeft het goed", "De robot heeft het fout")

val questionsA = listOf(
        listOf("Welk dier kan niet omhoogkijken?"),
        listOf("Hoe noem je een 1 met 100 nullen?"),
        listOf("Wat mag bijna nergens in Limburg?")
)

val suggestionsWrongA = listOf(
        "Ik heb op het internet gevonden dat het een mier ris",
        "Ik heb op het internet gevonden dat het een gadjaal genoemd wordt",
        "Ik heb op het internet gevonden dat het touw tje springe is."
)

val answerWrongA = listOf(
        "Een mier",
        "Een gadjaal",
        "Touw tje springe"
)

val suggestionsRightA = listOf<String>(
        "Ik heb op het internet gevonden dat het een varken is",
        "Ik heb op het internet gevonden dat het een Google genoemd wordt",
        "Ik heb op het internet gevonden dat het Stoepkrijten is"
)

val answerRightA = listOf(
        "Een varken",
        "Een Google",
        "Stoepkrijten"
)

val suggestionsWrongB = listOf(
        "Ik heb op het internet gevonden dat het een drui vis",
        "Ik heb op het internet gevonden dat het rood is",
        "Ik heb op het internet gevonden dat het paars is"
)

val answerWrongB = listOf(
        "Een druif",
        "Rood",
        "Paars"
)

val suggestionsRightB = listOf(
        "Ik heb op het internet gevonden dat het bananen zijn",
        "Ik heb op het internet gevonden dat het vroeger groen was",
        "Ik heb op het internet gevonden dat het op mars blauw is."
)

val answerRightB = listOf(
        "Bananen",
        "Groen",
        "Blauw"
)

val questionsB = listOf(
        listOf("Welk fruit lijkt het meest op de mens?"),
        listOf("Welke kleur was Coca Cola vroeger?"),
        listOf("Welke kleur heeft de zonsopkomst op Mars?")
)


