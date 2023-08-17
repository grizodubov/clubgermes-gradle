package org.b2bConnect

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class PackageJsonToMobileUpgrade {
    static void main(String[] args) {

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}package.json"
        def inputFile = new File("$fileJson")

        def slurped = new JsonSlurper().parseText(inputFile.text)
        def builder = new JsonBuilder(slurped)

        builder.content.dependencies."@tauri-apps/api" = "next"
        inputFile.write(builder.toPrettyString())

    }
}