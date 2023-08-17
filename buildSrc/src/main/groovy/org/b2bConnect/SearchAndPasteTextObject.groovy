package org.b2bConnect

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@ToString
@TupleConstructor
class SearchAndPasteTextObject {

    String elementOfLastOccurToSearch
    String textToAdd
    String fileToSearchName
    String fileToPasteName
    Boolean startFromEndOfString

    def getAt(Integer index) {
        index == 0 ? elementOfLastOccurToSearch : index == 1 ? textToAdd : index == 2 ? fileToSearchName : index == 3 ? fileToPasteName : index == 4 ? startFromEndOfString : '--'
    }
}