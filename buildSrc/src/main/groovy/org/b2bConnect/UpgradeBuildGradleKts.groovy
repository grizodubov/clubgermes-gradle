package org.b2bConnect

import java.util.concurrent.TimeUnit

class UpgradeBuildGradleKts {
    static void main(String[] args) {

        def One = new PasteTextObject()

        One.elementOfLastOccurToSearch = "plugins"
        One.textToAdd = "import java.util.Properties\n" as String
        One.fileToSearchName = "/src-tauri/gen/android/app/build.gradle.kts"
        One.fileToPasteName = "/src-tauri/gen/android/app/build.gradle.kts"
        One.startBeforeOccurrence = true
        One.startFromEndOfString = false
        One.startFromNewString = false

        PasteTextBySearch.main(One)

        def Two = new PasteTextObject()

        Two.elementOfLastOccurToSearch = "plugins"
        Two.textToAdd = "import java.io.FileInputStream\n\n" as String
        Two.fileToSearchName = "/src-tauri/gen/android/app/build.gradle.kts"
        Two.fileToPasteName = "/src-tauri/gen/android/app/build.gradle.kts"
        Two.startBeforeOccurrence = true
        Two.startFromEndOfString = false
        Two.startFromNewString = false

        PasteTextBySearch.main(Two)

        def Tree = new PasteTextObject()

        Tree.elementOfLastOccurToSearch = "android {"
        Tree.textToAdd = '''
val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))\n
'''
        Tree.fileToSearchName = "/src-tauri/gen/android/app/build.gradle.kts"
        Tree.fileToPasteName = "/src-tauri/gen/android/app/build.gradle.kts"
        Tree.startBeforeOccurrence = true
        Tree.startFromEndOfString = false
        Tree.startFromNewString = false

        PasteTextBySearch.main(Tree)

        def Four = new PasteTextObject()

        Four.elementOfLastOccurToSearch = "buildTypes {"
        Four.textToAdd = '''  signingConfigs {
    create("release") {
      keyAlias = localProperties["keyAlias"] as String
      keyPassword = localProperties["keyPassword"] as String
      storeFile = file(localProperties["storeFile"] as String)
      storePassword = localProperties["storePassword"] as String
    }
  }
'''
        Four.fileToSearchName = "/src-tauri/gen/android/app/build.gradle.kts"
        Four.fileToPasteName = "/src-tauri/gen/android/app/build.gradle.kts"
        Four.startBeforeOccurrence = true
        Four.startFromEndOfString = false
        Four.startFromNewString = false

        PasteTextBySearch.main(Four)

        def Five = new PasteTextObject()

        Five.elementOfLastOccurToSearch = "getByName(\"release\") {"
        Five.textToAdd = '''      signingConfig = signingConfigs.getByName("release")'''
        Five.fileToSearchName = "/src-tauri/gen/android/app/build.gradle.kts"
        Five.fileToPasteName = "/src-tauri/gen/android/app/build.gradle.kts"
        Five.startBeforeOccurrence = false
        Five.startFromEndOfString = true
        Five.startFromNewString = true

        PasteTextBySearch.main(Five)

//----------------------------------------------------------------
//----------------------------------------------------------------
        def fileToPasteName = '/src-tauri/gen/android/app/build.gradle.kts'
        def inputFilePath = new InputFilePathGet().inputFilePath(fileToPasteName)
        def file = new File(inputFilePath)
        def fileContent = file.text.replaceAll('isMinifyEnabled = true', 'isMinifyEnabled = false')
        // check if the line containing "versionCode" exists
        if (fileContent =~ /versionCode\s*=\s*\d+/) {
            // extract the current version code from the line
            def matcher = fileContent =~ /versionCode\s*=\s*(\d+)/
            def versionCode = Integer.parseInt(matcher[0][1])

            // prompt user for new version code
            def fg = 30
            def bg = 46
            def style = "${(char) 27}[$fg;$bg" + "m"
            println(style + "Current Android application version ${versionCode}. To increment version press 'y'.")

            ConsoleInput con = new ConsoleInput(
                    Integer.parseInt("1"),
                    Integer.parseInt("10"),
                    TimeUnit.SECONDS
            )

            def input = con.readLine()
            fg = 35
            bg = 49
            style = "${(char) 27}[$fg;$bg" + "m"
            System.out.println(style + "Done. Your input was: " + input)
//https://www.javaspecialists.eu/archive/Issue153-Timeout-on-Console-Input.html

//            Scanner scanner = new Scanner(System.in)
//            while (!scanner.hasNext()) {
//                Thread.sleep(100) {
//                    }
//                char versionCodeIncrement = scanner.next().charAt(0)

            if (input == 'y') {
                versionCode++
            }
            // update the version code in the file
            fileContent = fileContent.replaceFirst(/versionCode\s*=\s*\d+/, "versionCode = $versionCode")

            // write the updated content back to the file
            file.write(fileContent)
        }
    }
}
