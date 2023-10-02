package org.b2bConnect

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.StringEscapeUtils

class UpgradeTauriConfJsonNew {
    static void main(String[] args) {

        def variablesMap = [
                "Гермес"                     : ["package", "productName"],
                "Business"                   : ["tauri", "bundle", "category"],
                "ru.clubgermes.social.client": ["tauri", "bundle", "identifier"]
        ]

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}/src-tauri/tauri.conf.json"
        def inputFile = new File(fileJson)
        def jsonData = new JsonSlurper().parseText(inputFile.getText())
        def JsonBuilder = new JsonBuilder(jsonData)

        for (variablesMapElement in variablesMap) {
            jsonDataUpdate(JsonBuilder, variablesMapElement)
            println("${(char) 27}[35;49" + "m" + variablesMapElement.value + " = " + variablesMapElement.key)
        }

        JsonBuilder.content.tauri.bundle.iOS = {
            developmentTeam "XRF3344MB3"
        }
        println("${(char) 27}[35;49" + "m" + "[tauri, bundle, iOS, developmentTeam] added and set to XRF3344MB3")
        JsonBuilder.content.tauri.remove('allowlist')
        println("${(char) 27}[35;49" + "m" + "[allowlist] removed")
        JsonBuilder.content.tauri.remove('updater')
        println("${(char) 27}[35;49" + "m" + "[updater] removed")

        inputFile.setText(StringEscapeUtils.unescapeJavaScript(JsonBuilder.toPrettyString()))
    }

    static jsonDataUpdate(JsonBuilder, variablesMapElement) {

        def valuesArraySize = variablesMapElement.value.size()
        switch (valuesArraySize) {
            case 1:
                def p = variablesMapElement.value[0]
                JsonBuilder.content["$p"] = variablesMapElement.key
                break
            case 2:
                def p = variablesMapElement.value[0]
                def r = variablesMapElement.value[1]
                JsonBuilder.content["$p"]["$r"] = variablesMapElement.key
                break
            case 3:
                def p = variablesMapElement.value[0]
                def r = variablesMapElement.value[1]
                def s = variablesMapElement.value[2]
                JsonBuilder.content["$p"]["$r"]["$s"] = variablesMapElement.key
                break
            case 4:
                def p = variablesMapElement.value[0]
                def r = variablesMapElement.value[1]
                def s = variablesMapElement.value[2]
                def t = variablesMapElement.value[3]
                JsonBuilder.content["$p"]["$r"]["$s"]["$t"] = variablesMapElement.key
                break
            case 5:
                def p = variablesMapElement.value[0]
                def r = variablesMapElement.value[1]
                def s = variablesMapElement.value[2]
                def t = variablesMapElement.value[3]
                def u = variablesMapElement.value[4]
                JsonBuilder.content["$p"]["$r"]["$s"]["$t"]["$u"] = variablesMapElement.key
                break
        }
    }

}
