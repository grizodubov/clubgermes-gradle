package org.b2bConnect

import java.nio.file.Files
import java.nio.file.Path

class ReplaceIconPackAndroid {
    static void main(String[] args) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def pathSource = "${path}/buildSrc/src/main/filesTauriMobile/icons ios android/res/*.*"
        def pathTarget = "${path}/src-tauri/gen/android/app/src/main/res"

        Files.copy(pathSource as Path, pathTarget as Path)
    }
}
