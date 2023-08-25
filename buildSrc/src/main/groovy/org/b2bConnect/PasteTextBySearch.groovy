package org.b2bConnect

class PasteTextBySearch {
    static void main(SearchAndPasteTextObject a) {

        def newString = insertStringInIndexPlace(a)
        if (newString.length() > 0) {
            WrightToFileFunc.main(a.fileToPasteName, newString)
        }
    }

    static searchLastOccurIndex(a) {
        def arrayToSearch = new InputFileTextGet().inputFileText(a.fileToSearchName)
        def indexOfLastOccurIndex = arrayToSearch.lastIndexOf("$a.elementOfLastOccurToSearch")
        def indexOfLastOccurIndexEndOfString = arrayToSearch.indexOf("\n", indexOfLastOccurIndex)
        if (a.startFromEndOfString) {

            return indexOfLastOccurIndexEndOfString
        } else {

            return indexOfLastOccurIndex + a.elementOfLastOccurToSearch.length()
        }
    }

    static insertStringInIndexPlace(a) {
        def indexOfLastOccurIndexEndOfString = searchLastOccurIndex(a)
        def inputFileText = new InputFileTextGet().inputFileText(a.fileToSearchName)
        StringBuilder sb = new StringBuilder(inputFileText)
        if (!sb.contains(a.textToAdd as String)) {
            sb.insert(indexOfLastOccurIndexEndOfString as int, a.textToAdd as String) // to new line
            if (a.startFromNewString) {
                sb.insert(indexOfLastOccurIndexEndOfString as int, "\n" as String)
            }
            def newString = sb.toString()

            return newString
        } else {
            println "$a.textToAdd\n---------------already exists---------------"

            return ""
        }
    }
}
