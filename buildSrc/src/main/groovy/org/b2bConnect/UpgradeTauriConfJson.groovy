package org.b2bConnect

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.StringEscapeUtils

class UpgradeTauriConfJson {
    static void main(String[] args) {

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}/src-tauri/tauri.conf.json"
        def inputFile = new File(fileJson)

//        def generator = new JsonGenerator.Options().disableUnicodeEscaping().build()

        def jsonData = new JsonSlurper().parseText(inputFile.getText())
        def JsonBuilder = new JsonBuilder(jsonData)

//        builder.content.dependencies."@tauri-apps/api" = "next"
        JsonBuilder.content.package.productName = "Гермес"
        JsonBuilder.content.tauri.bundle.category = "Business"
        JsonBuilder.content.tauri.bundle.identifier = "ru.clubgermes.social.client"
        JsonBuilder.content.tauri.bundle.iOS = {
            developmentTeam "XRF3344MB3"
        }
        JsonBuilder.content.tauri.remove('allowlist')
        JsonBuilder.content.tauri.remove('updater')

//        println generator.toJson(jsonData)
//        println new JsonBuilder(jsonData, generator).toString()
//        inputFile.setText(JsonBuilder.toPrettyString())
        inputFile.setText(StringEscapeUtils.unescapeJavaScript(JsonBuilder.toPrettyString()))
//        inputFile.setText(JsonOutput.prettyPrint(JsonBuilder.toString()))
    }
}
