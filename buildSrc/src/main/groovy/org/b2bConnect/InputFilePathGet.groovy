package org.b2bConnect

class InputFilePathGet {
    static void main(String[] args) {
        inputFilePath("vite.config.ts")
    }

    static inputFilePath(fileName) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def filePath = "${path}$fileName"

//        println filePath
        return filePath
    }
}
