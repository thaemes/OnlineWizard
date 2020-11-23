package furhatos.app.onlinewizard

import furhatos.app.onlinewizard.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class OnlinewizardSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
