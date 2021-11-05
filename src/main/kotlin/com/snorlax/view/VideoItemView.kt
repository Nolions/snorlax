package com.snorlax.view

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextArea
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class VideoItemView : View() {
    private val coverPath = SimpleStringProperty()
    private val coverFile = SimpleObjectProperty<Image>()

    private val videoPath = SimpleStringProperty()
    private val videoFile = SimpleObjectProperty<Image>()

    private val coverFilterList = listOf("*.png", "*.jpg")
    private val videoFilterList = listOf("*.mp4", "*.avi")

    private var dirPath = "."

    private val name = SimpleStringProperty()
    private val category = SimpleStringProperty()

    private val rootWidth = 500.0
    private val coverLayoutWidth = 100.0

    private val defaultCover = Image(
        ClassLoader.getSystemResourceAsStream("ic_picture.png"),
        50.0,
        50.0,
        false,
        false
    )

    // mock data
    private val categories = FXCollections.observableArrayList("分類1", "分類2", "分類3", "分類4", "分類5")

    init {
        coverFile.value = defaultCover
    }

    override val root = vbox {
        prefWidth = rootWidth
        paddingAll = 4
        spacing = 4.0

        hbox {
            fitToParentWidth()
            spacing = 4.0

            this += coverLayout()
            this += mediaParamLayoutView()
        }

        this += mediaChoseView()

        // 影片描述
        this += mediaDescribeLayout()
    }

    /**
     * 影片描述
     */
    private fun mediaDescribeLayout(): TextArea {
        return textarea() {
            fitToParentWidth()
            prefHeight = 60.0
        }
    }

    /**
     * 封面View
     */
    private fun coverLayout(): Pane {
        return vbox {
            fitToParentWidth()
            imageview(coverFile) {
                fitWidth = coverLayoutWidth
                fitHeight = fitWidth
            }
        }
    }

    /**
     * 影片參數設置View
     */
    private fun mediaParamLayoutView(): Pane {
        val viewWidth = rootWidth - coverLayoutWidth
        return vbox {
            spacing = 4.0
            prefWidth = viewWidth

            hbox {
                spacing = 4.0
                // 影片名稱
                textfield(name) {
                    prefWidth = viewWidth / 3 * 2
                    promptText = "影片名稱"
                }

                // 影片類型
                combobox(category, categories) {
                    prefWidth = viewWidth / 3 * 1
                    promptText = "影片類型"
                }
            }
        }
    }

    /**
     * 影片選擇View
     */
    private fun mediaChoseView(): HBox {
        return hbox {
            fitToParentWidth()
            spacing = 4.0
            textfield(videoPath) {
                prefWidth = rootWidth / 4 * 3
            }

            button("Video Chose") {
                prefWidth = rootWidth / 4 * 1
                action {
                    chooseVideo()
                }
            }
        }
    }

    /**
     * 封面圖片選擇
     */
    private fun chooseCover() {
        val fileChooser = chooserFiles(coverFilterList)

        if (fileChooser.isNotEmpty()) {
            val imgPath = fileChooser.first().toString().replace("\\", "/")
            coverPath.value = imgPath
            coverFile.value = Image(File(imgPath).inputStream())

            log.info("file: $imgPath")
        }
    }

    /**
     * 上傳影片選擇
     */
    private fun chooseVideo() {
        val fileChooser = chooserFiles(videoFilterList)

        if (fileChooser.isNotEmpty()) {
            val imgPath = fileChooser.first().toString().replace("\\", "/")
            videoPath.value = imgPath
            videoFile.value = Image(File(imgPath).inputStream())
            log.info("file: $imgPath")
        }
    }

    /**
     * 檔案選擇
     */
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
