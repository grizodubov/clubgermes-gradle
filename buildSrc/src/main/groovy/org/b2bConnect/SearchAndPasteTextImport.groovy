package org.b2bConnect

class SearchAndPasteTextImport {
    static void main(SearchAndPasteTextObject a) {

        def newString = insertStringInIndexPlace(a.elementOfLastOccurToSearch, a.textToAdd, a.fileToSearchName, a.startFromEndOfString)
        if (newString.length() > 0) {
            wrightToFileFunction(a.fileToPasteName, newString)
        }
    }

    static inputFilePath(fileName) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileJson = "${path}$fileName"

        return fileJson
    }

    static inputFileText(fileToSearchName) {
        def fileJson = inputFilePath(fileToSearchName)
        def inputFile = new File("$fileJson")
        def arrayToSearch = inputFile.text

        return arrayToSearch
    }

    static searchLastOccurIndex(elementOfLastOccurToSearch, fileToSearchName, startFromEndOfString) {
        def arrayToSearch = inputFileText(fileToSearchName)
        def indexOfLastOccurIndex = arrayToSearch.lastIndexOf("$elementOfLastOccurToSearch")
        def indexOfLastOccurIndexEndOfString = arrayToSearch.indexOf("\n", indexOfLastOccurIndex)
        if (startFromEndOfString) {

            return indexOfLastOccurIndexEndOfString
        } else {

            return indexOfLastOccurIndex + elementOfLastOccurToSearch.length()
        }
    }

    static insertStringInIndexPlace(elementOfLastOccurToSearch, textToAdd, fileToSearchName, startFromEndOfString) {
        def indexOfLastOccurIndexEndOfString = searchLastOccurIndex(elementOfLastOccurToSearch, fileToSearchName, startFromEndOfString)
        StringBuilder sb = new StringBuilder(inputFileText(fileToSearchName))
        if (!sb.contains(textToAdd)) {
            sb.insert(indexOfLastOccurIndexEndOfString, textToAdd) // to new line
            def newString = sb.toString()

            return newString
        } else {
            println "$textToAdd\n---------------already exists---------------"

            return ""
        }
    }

    static wrightToFileFunction(fileToPasteName, newString) {
        def inputFilePath = inputFilePath(fileToPasteName)
        def inputFile = new File("$inputFilePath")
        inputFile.write(newString)
    }
}
