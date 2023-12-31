package org.b2bConnect

class PasteTextBySearch {
    static void main(PasteTextObject a) {

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
            if (a.startBeforeOccurrence) {
                return indexOfLastOccurIndex
            } else {

                return indexOfLastOccurIndex + a.elementOfLastOccurToSearch.length()
            }

        }
    }

    static insertStringInIndexPlace(a) {
        def indexOfLastOccurIndexEndOfString = searchLastOccurIndex(a)
        def inputFileText = new InputFileTextGet().inputFileText(a.fileToSearchName)
        StringBuilder sb = new StringBuilder(inputFileText)

        def fg = 35
        def bg = 49
        def style = "${(char) 27}[$fg;$bg" + "m"

        if (!sb.contains(a.textToAdd as String)) {
            sb.insert(indexOfLastOccurIndexEndOfString as int, a.textToAdd as String)
            if (a.startFromNewString) {
                sb.insert(indexOfLastOccurIndexEndOfString as int, "\n" as String)
            }
            def newString = sb.toString()

            println(style + "$a.textToAdd\n--------------- text added to $a.fileToPasteName ---------------")

            return newString
        } else {
            println(style + "$a.textToAdd\n--------------- already exists in $a.fileToSearchName ---------------")

            return ""
        }
    }
}
