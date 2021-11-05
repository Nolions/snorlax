package com.snorlax

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val listView by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 16.px
            fontWeight = FontWeight.BOLD
        }

        listView {
            focused {
                backgroundColor += Color.TRANSPARENT
            }
        }
    }
}