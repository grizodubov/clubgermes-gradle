package org.b2bConnect

class PasteTextByIndex {
    static void main(fileToPasteName, textToAdd, indexWhereToPaste, startFromNewString) {

        def newString = insertStringInIndexPlace(fileToPasteName, textToAdd, indexWhereToPaste, startFromNewString)
        if (newString.length() > 0) {
            WrightToFileFunc.main(fileToPasteName, newString)
        }
    }

    static insertStringInIndexPlace(fileToPasteName, textToAdd, indexWhereToPaste, startFromNewString) {
        def inputFileText = new InputFileTextGet().inputFileText(fileToPasteName)
        StringBuilder sb = new StringBuilder(inputFileText)
        sb.insert(indexWhereToPaste as int, textToAdd as String) // to new line
        def newString = sb.toString()

        return newString

    }
}
