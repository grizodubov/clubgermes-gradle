package org.b2bConnect

class PasteTextBySearch {
    static void main(SearchAndPasteTextObject a) {

        def newString = insertStringInIndexPlace(a.elementOfLastOccurToSearch, a.textToAdd, a.fileToSearchName, a.startFromEndOfString)
        if (newString.length() > 0) {
            WrightToFileFunc.main(a.fileToPasteName, newString)
        }
    }

    static searchLastOccurIndex(elementOfLastOccurToSearch, fileToSearchName, startFromEndOfString) {
        def arrayToSearch = new InputFileTextGet().inputFileText(fileToSearchName)
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
        def inputFileText = new InputFileTextGet().inputFileText(fileToSearchName)
        StringBuilder sb = new StringBuilder(inputFileText)
        if (!sb.contains(textToAdd as String)) {
            sb.insert(indexOfLastOccurIndexEndOfString as int, textToAdd as String) // to new line
            def newString = sb.toString()

            return newString
        } else {
            println "$textToAdd\n---------------already exists---------------"

            return ""
        }
    }
}
