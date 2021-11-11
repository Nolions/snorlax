package com.snorlax.view

import com.snorlax.Styles
import com.snorlax.lib.NoSelectionModel
import com.snorlax.model.Video
import javafx.collections.FXCollections
import tornadofx.*

/**
 * 主頁面
 */
class MainView : View("Snorlax") {
    private val videos = FXCollections.observableArrayList<Video>()

    override val root = borderpane {
        videos.add(Video())

        prefWidth = 540.0
        prefHeight = 700.0

        // menu bat
        top = vbox {

        }
        // content
        center = contentLayout()
        // status bar
        bottom = vbox {

        }
    }

    private fun contentLayout() = vbox {
        label("影片批次上傳") {
            addClass(Styles.heading)
        }

        listview(videos) {
            prefHeight = 600.0
//            addClass(Styles.listView)
            selectionModel = NoSelectionModel()
            cellFormat {
                graphic = VideoItemView().root
            }
        }

        button("開始上傳"){
            action {
                log.info("======== Start Update ======== ")
            }
        }
    }
}
