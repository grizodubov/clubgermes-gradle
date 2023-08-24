package org.b2bConnect

class WrightToFileFunc {
    static void main(fileToPasteName, newString) {

        def inputFilePath = new InputFilePathGet().inputFilePath(fileToPasteName)
        def inputFile = new File("$inputFilePath")
        inputFile.write(newString as String)
    }
}
