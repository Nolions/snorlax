package com.snorlax

import com.snorlax.view.LoginView
import com.snorlax.view.MainView
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(LoginView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)

        // 設置APP Icon
        stage.icons.add(Image(ClassLoader.getSystemResourceAsStream("app.png")))
        // 固定視窗大小
        stage.isResizable = false
        stage.show()
    }
}