package org.b2bConnect

class InputFilePathGet {
    static void main(String[] args) {

    }

    static inputFilePath(fileName) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def filePath = "${path}$fileName"

//        println filePath
        return filePath
    }
}
