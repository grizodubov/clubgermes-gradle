package org.b2bConnect

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.StringEscapeUtils

class UpgradeTauriConfJsonCopy {
    static void main(String[] args) {

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}/src-tauri/tauri.conf.json"
        def inputFile = new File(fileJson)

        def variablesArray = [
                "Гермес"                     : ["package", "productName"],
                "Business"                   : ["tauri", "bundle", "category"],
                "ru.clubgermes.social.client": ["tauri", "bundle", "identifier"]
        ]

//        def generator = new JsonGenerator.Options().disableUnicodeEscaping().build()

        def jsonData = new JsonSlurper().parseText(inputFile.getText())
        def JsonBuilder = new JsonBuilder(jsonData)

        for (i in variablesArray) {
            if (i.value.size() == 2) {
                def p = i.value[0]
                def r = i.value[1]
                JsonBuilder.content."$p"."$r" = i.key
            }
        }
//        println("${(char) 27}[36;49" + "m" + JsonBuilder.content[packageProductName[0]][packageProductName[1]])


//        if (JsonBuilder.content.{${someVariable}} != packageProductName[0]) {
//            println(style + "tauri.conf.json ${packageProductName[1]} is set to: \"${packageProductName[0]}\"")
//        } else {
//            println(style + "tauri.conf.json ${packageProductName[1]} is up to date (\"${packageProductName[0]}\")")
//        }
//        builder.content.dependencies."@tauri-apps/api" = "next"
//        JsonBuilder.content[packageProductName[0]][packageProductName[1]] = packageProductName[2]
//
//        if (JsonBuilder.content.tauri.bundle.category != tauriBundleCategory) {
//            println(style + "tauri.conf.json category=$JsonBuilder.content.tauri.bundle.category is set to: \"$tauriBundleCategory\"")
//        } else {
//            println(style + "tauri.conf.json category is up to date (\"$tauriBundleCategory\")")
//        }
//        JsonBuilder.content.tauri.bundle.category = tauriBundleCategory
//
//        if (JsonBuilder.content.tauri.bundle.identifier != tauriBundleIdentifier) {
//            println(style + "tauri.conf.json identifier=$JsonBuilder.content.tauri.bundle.identifier is set to: \"$tauriBundleIdentifier\"")
//        } else {
//            println(style + "tauri.conf.json identifier is up to date (\"$tauriBundleIdentifier\")")
//        }
//        JsonBuilder.content.tauri.bundle.identifier = tauriBundleIdentifier

//        JsonBuilder.content.tauri.bundle.iOS = {
//            developmentTeam "XRF3344MB3"
//        }
//        JsonBuilder.content.tauri.remove('allowlist')
//        JsonBuilder.content.tauri.remove('updater')

//        println generator.toJson(jsonData)
//        println new JsonBuilder(jsonData, generator).toString()
//        inputFile.setText(JsonBuilder.toPrettyString())
        inputFile.setText(StringEscapeUtils.unescapeJavaScript(JsonBuilder.toPrettyString()))
//        inputFile.setText(JsonOutput.prettyPrint(JsonBuilder.toString()))
    }

/*    static printChangesWithStyle(name) {
        if (JsonBuilder.content.package.productName != packageProductName) {
            println(style + "tauri.conf.json productName=$JsonBuilder.content.package.productName is set to: \"$packageProductName\"")
        } else {
            println(style + "tauri.conf.json productName is up to date (\"$packageProductName\")")
        }
    }*/
}
