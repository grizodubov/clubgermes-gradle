package org.b2bConnect

class InputFileTextGet {
    static void main(String[] args) {

    }

    static inputFileText(fileToSearchName) {
        def path = new InputFilePathGet().inputFilePath(fileToSearchName)
        def inputFile = new File("$path")
        def arrayToSearch = inputFile.text

        return arrayToSearch
    }
}
