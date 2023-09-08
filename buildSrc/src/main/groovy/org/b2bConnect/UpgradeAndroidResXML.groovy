package org.b2bConnect

import groovy.xml.XmlParser
import groovy.xml.XmlSlurper

class UpgradeAndroidResXML {
    static void main(String[] args) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}/src-tauri/gen/android/app/src/main/res/values/strings.xml"
        def inputFile = new File("$fileJson")

        def xml = new XmlSlurper().parse(inputFile)
        def appName = xml.'**'.find { it.name() == 'app_name' }?.text() ?: ''
        println appName
//        def appNameResource = xml.xpath('/resources/string[@name="app_name"]')
//        new FileWriter(inputFile).write(xml.toString())
    }
}
