package org.b2bConnect

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@ToString
@TupleConstructor
class PasteTextObject {

    String elementOfLastOccurToSearch
    String textToAdd
    String fileToSearchName
    String fileToPasteName
    Boolean startBeforeOccurrence
    Boolean startFromEndOfString
    Boolean startFromNewString

    def getAt(Integer index) {
        index == 0 ? elementOfLastOccurToSearch : index == 1 ? textToAdd : index == 2 ? fileToSearchName : index == 3 ? fileToPasteName : index == 4 ? startBeforeOccurrence : index == 5 ? startFromEndOfString : index == 6 ? startFromNewString : '--'
    }
}