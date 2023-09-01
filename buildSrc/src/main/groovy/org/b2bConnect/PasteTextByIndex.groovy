package org.b2bConnect

class PasteTextByIndex {
    static void main(PasteTextObject a, indexWhereToPaste) {

        def newString = insertStringInIndexPlace(a.fileToPasteName, a.textToAdd, a.startFromNewString, indexWhereToPaste)
        if (newString.length() > 0) {
            WrightToFileFunc.main(a.fileToPasteName, newString)
        }
    }

    static insertStringInIndexPlace(fileToPasteName, textToAdd, startFromNewString, indexWhereToPaste) {
        def inputFileText = new InputFileTextGet().inputFileText(fileToPasteName)
        StringBuilder sb = new StringBuilder(inputFileText)
        sb.insert(indexWhereToPaste as int, textToAdd as String)
        if (startFromNewString) {
            sb.insert(indexWhereToPaste as int, "\n" as String)
        }
        def newString = sb.toString()

        return newString
    }
}