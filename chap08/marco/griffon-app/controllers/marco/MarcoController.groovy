package marco

import griffon.util.EventPublisher

@EventPublisher
class MarcoController {
    def model

    void mvcGroupInit(Map args) {
        def createAPlayerAndRegisterIt = { id ->
            def player = new Player(id)
            addEventListener(player)
            player.addEventListener(delegate)
        }
        (1..3).each { createAPlayerAndRegisterIt(it) }
        addEventListener(new Player(4))
        new Player(5)
    }

    def marco = { evt = null ->
        execSync { model.output = '' }
        publishEvent 'Marco'
    }

    def onPolo = { msg ->
        execAsync { model.output += msg + '\n' }
    }
}