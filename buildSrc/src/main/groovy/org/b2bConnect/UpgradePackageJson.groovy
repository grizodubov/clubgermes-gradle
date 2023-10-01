package org.b2bConnect

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class UpgradePackageJson {
    static void main(String[] args) {

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}package.json"
        def inputFile = new File("$fileJson")

        def slurped = new JsonSlurper().parseText(inputFile.text)
        def builder = new JsonBuilder(slurped)

        def valueOld = builder.content.dependencies."@tauri-apps/api"
        def fg = 35
        def bg = 49
        def style = "${(char) 27}[$fg;$bg" + "m"
        if (valueOld == "next") {
            println(style + "Dependency \"@tauri-apps/api\" already up to date: \"$valueOld\"")
        } else {
            builder.content.dependencies."@tauri-apps/api" = "next"
            def valueNew = builder.content.dependencies."@tauri-apps/api"
            println(style + "Dependency \"@tauri-apps/api\" updated: \"$valueOld\" --> \"$valueNew\"")
        }
        inputFile.write(builder.toPrettyString())

    }
}