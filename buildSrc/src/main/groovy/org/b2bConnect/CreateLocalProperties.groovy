package org.b2bConnect

class CreateLocalProperties {
    static void main(String[] args) {
        def localPropertiesFileText = '''
## This file must *NOT* be checked into Version Control Systems,
# as it contains information specific to your local configuration.
#
# Location of the SDK. This is only used by Gradle.
# For customization when using a Version Control System, please read the
# header note.
#Fri Apr 21 13:23:30 MSK 2023
sdk.dir=/Users/valeriigrizodubov/Library/Android/sdk
storePassword=02@Enteraboksal
keyPassword=02@Enteraboksal
keyAlias=digitender-key0
storeFile=/Users/valeriigrizodubov/Documents/keystores/upload-keystore
'''
        def projectDirPath = new ProjectDirUriGet().ProjectDirUri()
        new File("$projectDirPath/src-tauri/gen/android/local.properties").withWriter { writer -> writer.write(localPropertiesFileText) }

        def fg = 35
        def bg = 49
        def style = "${(char) 27}[$fg;$bg" + "m"
        println(style + "File created [project]/src-tauri/gen/android/local.properties")
    }
}