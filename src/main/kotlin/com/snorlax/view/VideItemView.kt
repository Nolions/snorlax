package com.snorlax.view

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class VideItemView : View("My View") {
    private val coverPath = SimpleStringProperty()
    private val coverFile = SimpleObjectProperty<Image>()

    private val videoPath = SimpleStringProperty()
    private val videoFile = SimpleObjectProperty<Image>()

    private val coverFilterList = listOf("*.png", "*.png")
    private val videoFilterList = listOf("*.mp4", "*.avi")

    private var dirPath = "."

    private val name = SimpleStringProperty()

    private val categories = FXCollections.observableArrayList(
        "Tag1",
        "Tag2", "Tag3", "Tag4", "Tag5"
    )

    private val selectedCategory = SimpleStringProperty()

    private val defaultCover = Image(
        ClassLoader.getSystemResourceAsStream("ic_picture.png"),
        50.0,
        50.0,
        false,
        false
    )

    init {
        coverFile.value = defaultCover
    }

    override val root = vbox {
        paddingAll = 8
        spacing = 4.0

        hbox {
            spacing = 4.0

            this += coverView()

            this += mediaParamView()
        }

        this += mediaChoseView()

        textarea() {
            prefHeight = 60.0
            prefWidth = 400.0
        }
    }

    /**
     * 封面選擇View
     */
    private fun coverView(): Pane {
        return vbox {
            imageview(coverFile) {
                fitHeight = 60.0
                fitWidth = 60.0
            }

            button("Cover") {
                prefWidth = 60.0
                prefHeight = 20.0
                action {
                    chooseCover()
                }
            }
        }
    }

    /**
     * 影片參數設置View
     */
    private fun mediaParamView():Pane {
        return vbox {
            spacing = 4.0
            prefWidth = 340.0
            textfield(name)
            combobox(selectedCategory, categories)
            flowpane {
                spacing = 4.0
                for (i in 1..10) {
                    this += checkbox("Tag$i") {
                        action {
                            println("Checkbox Tag$i")
                        }
                    }
                }
            }
        }
    }

    /**
     * 影片選擇View
     */
    private fun mediaChoseView():Pane {
        return hbox {
            spacing = 4.0
            textfield(videoPath) {
                isEditable = false
                prefWidth = 300.0
            }

            button("Video Chose") {
                prefWidth = 100.0

                action {
                    chooseVideo()
                }
            }
        }
    }

    private fun chooseCover() {
        val fileChooser = chooserFiles(coverFilterList)

        if (fileChooser.isNotEmpty()) {
            val imgPath = fileChooser.first().toString().replace("\\", "/")
            coverPath.value = imgPath
            coverFile.value = Image(File(imgPath).inputStream())

            log.info("file: $imgPath")
        }
    }

    private fun chooseVideo() {
        val fileChooser = chooserFiles(videoFilterList)

        if (fileChooser.isNotEmpty()) {
            val imgPath = fileChooser.first().toString().replace("\\", "/")
            videoPath.value = imgPath
            videoFile.value = Image(File(imgPath).inputStream())
            log.info("file: $imgPath")
        }
    }

    private fun chooserFiles(
        extensions: List<String>,
        title: String = "Chose Image",
        mode: FileChooserMode = FileChooserMode.Single
    ): List<File> {
        val filters = arrayOf(FileChooser.ExtensionFilter("$extensions", extensions))

        return chooseFile(
            title = title,
            filters = filters,
            mode = mode
        ) {
            initialDirectory = File(dirPath)
        }
    }
}
