package org.b2bConnect

import groovy.xml.XmlParser
import groovy.xml.XmlNodePrinter

class UpgradeAndroidResXML {
    static void main(String[] args) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def filePath = "${path}/src-tauri/gen/android/app/src/main/res/values/strings.xml"

        def xmlFile = new XmlParser().parse(filePath)
//        def appNameAttr = xmlFile.find { it.author.'@name'.text() == "first" }.author.text()
        def appNameAttr = xmlFile.find { it.'@name' == "app_name" }?.value() ?: ''
        appNameAttr[0] = 'Гермес'
        def appMainActivityTitle = xmlFile.find { it.'@name' == "main_activity_title" }?.value() ?: ''
        appMainActivityTitle[0] = 'Гермес'

        def fg = 35
        def bg = 49
        def style = "${(char) 27}[$fg;$bg" + "m"
        println(style + "app_name = Гермес in /src-tauri/gen/android/app/src/main/res/values/strings.xml" )
        println(style + "main_activity_title = Гермес in /src-tauri/gen/android/app/src/main/res/values/strings.xml")

        new File(filePath).withWriter {
            def printer = new XmlNodePrinter(new PrintWriter(it))
            printer.preserveWhitespace = true
            printer.print(xmlFile)
        }
    }
}
