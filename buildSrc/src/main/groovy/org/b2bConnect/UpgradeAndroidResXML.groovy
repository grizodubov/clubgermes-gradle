package org.b2bConnect

import groovy.xml.XmlParser
import groovy.xml.XmlSlurper

class UpgradeAndroidResXML {
    static void main(String[] args) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def filePath = "${path}/src-tauri/gen/android/app/src/main/res/values/strings.xml"

        def xmlFile = new XmlParser().parse(filePath)
//        def first = xmlFile.find { it.author.'@name'.text() == "first" }.author.text()
        xmlFile.find { it.'@name' == "app_name" }?.text() ?: ''
        def second = xmlFile.find { it.'@name' == "app_name" }?.text() ?: ''
        println second.toString()
        println xmlFile


//        new FileWriter(inputFile).write(xml.toString())
    }
}
