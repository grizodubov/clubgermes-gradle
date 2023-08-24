package org.b2bConnect

class SearchMissingBracket {
    static void main(fileToSearchName) {

    }

    static searchLostBracketIndex(String fileToSearchName) {
        def arrayToSearch = InputFileTextGet.inputFileText(fileToSearchName)
        def iNextOpen = arrayToSearch.indexOf('{', 0)
        def iNextClose = arrayToSearch.indexOf('}', 0)
        def iOpenLast = arrayToSearch.lastIndexOf('{', arrayToSearch.length())
        def iCloseLast = arrayToSearch.lastIndexOf('}', arrayToSearch.length())

        def level = 0

        if (iNextOpen < iNextClose) {
            if (iOpenLast < iCloseLast) {
                while (iNextOpen > 0) { // iterate until there is no more Open bracket
                    if (iNextOpen < iNextClose) { // iterate until the next Close bracket is found
                        ++level // count how many Open brackets go in a row
                        println "open at:  $iNextOpen, level: $level, and iNextClose: $iNextClose"
                        if (iNextClose == iCloseLast) { // last Close bracket is found
                            --level
                            println "Last close at: $iNextClose, level: $level"
                        } else if (iNextOpen == iOpenLast) {
                            while (iNextClose > 0) { // iterate until the end of file
                                --level // count how many Close brackets go in a row
                                println "close at: $iNextClose, level: $level"
                                iNextClose = arrayToSearch.indexOf('}', iNextClose + 1)
                                if (iNextClose == iCloseLast) { // last Close bracket is found
                                    --level
                                    println "Last close at: $iNextClose, level: $level"
                                    break
                                }
                            }
                        }
                        iNextOpen = arrayToSearch.indexOf('{', iNextOpen + 1)
                    } else {
                        while (iNextOpen > iNextClose) { // iterate until the next Open bracket is found
                            --level // count how many open brackets go in a row
                            println "close at: $iNextClose, level: $level, and iNextOpen: $iNextOpen"
                            iNextClose = arrayToSearch.indexOf('}', iNextClose + 1)
                            def i = arrayToSearch.indexOf('}', iNextOpen + 1)
                            if (i == iOpenLast) { // last Open bracket is found
                                ++level
                                println "Last open at: $iOpenLast, level: $level, and iNextClose: $iNextClose"
                            }
                        }
                    }
                }
                if (level == 0) {
                    return "Curly Brackets OK!"
                } else if (level < 0) {
                    level = -level
                    return ["Open curly brackets are missing: $level", iOpenLast]
                } else {
                    return ["Close curly brackets are missing: $level", iCloseLast]
                }
            } else {
                return ["Lost closing bracket at the end of the file. $iOpenLast > $iCloseLast", iOpenLast]
            }
        } else {
            return ["Lost open bracket at the beginning of the file. $iOpenLast < $iCloseLast", iCloseLast]
        }
    }
}