package com.snorlax.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.image.Image
import tornadofx.*

/**
 * 登入頁
 */
class LoginView : View("Login") {
    private val username = SimpleStringProperty()
    private val password = SimpleStringProperty()

    override val root = form {
        fieldset {
            hbox {
                this += leftView()
                this += rightView()
            }
        }
    }

    private fun leftView(): Node {
        return imageview(
            Image(
                ClassLoader.getSystemResourceAsStream("login.png"),
                100.0,
                100.0,
                false,
                false
            )
        )
    }

    private fun rightView(): Node {
        return vbox {
            paddingTop = 8
            field("Username") {
                textfield(username)
            }

            field("Password") {
                textfield(password)
            }

            hbox {
                alignment = Pos.BASELINE_RIGHT
                button("登入") {
                    this.loginOnClick()
                }
            }
        }
    }

    private fun Button.loginOnClick() {
        enableWhen {
            username.isNotEmpty.and(password.isNotEmpty)
        }
        action {
            // TODO 登入驗證
            // TODO 切換View
            replaceWith(
                component = MainView::class,
                sizeToScene = true,
                centerOnScreen = true
            )
        }
    }
}
