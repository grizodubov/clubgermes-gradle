package org.b2bConnect

class SearchMissingBracket {
    static void main(String[] args) {
        println searchLostBracketIndex("vite.config.ts")[1]
    }

    static inputFilePath(fileName) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def filePath = "${path}$fileName"

        return filePath
    }

    static inputFileText(fileToSearchName) {
        def filePath = inputFilePath(fileToSearchName)
        def inputFile = new File("$filePath")
        def arrayToSearch = inputFile.text

        return arrayToSearch
    }

    static searchLostBracketIndex(fileToSearchName) {
        def arrayToSearch = inputFileText(fileToSearchName)
        def iNextOpen = arrayToSearch.indexOf('{', 0)
        def iNextClose = arrayToSearch.indexOf('}', 0)
        def iOpenLast = arrayToSearch.lastIndexOf('{', arrayToSearch.length())
        def iCloseLast = arrayToSearch.lastIndexOf('}', arrayToSearch.length())

        def level = 0

        if (iNextOpen < iNextClose) {
            if (iOpenLast < iCloseLast) {
                while (iNextOpen > 0) { // iterate until there is no more open bracket
                    if (iNextOpen < iNextClose) { // iterate until the next "}" bracket is found
                        ++level // count how many open brackets go in a row
                        println "open at:  $iNextOpen, level: $level, and iNextClose: $iNextClose"
                        iNextOpen = arrayToSearch.indexOf('{', iNextOpen + 1)
                        def i = arrayToSearch.indexOf('}', iNextClose + 1)
                        if ( i == iCloseLast) {
                            --level
                            println "close at: $iNextClose, level: $level, and iNextOpen: $iNextOpen"
                        }
                    } else {
                        while (iNextOpen > iNextClose) { // iterate until the next "}" bracket is found
                            --level // count how many open brackets go in a row
                            println "close at: $iNextClose, level: $level, and iNextOpen: $iNextOpen"
                            iNextClose = arrayToSearch.indexOf('}', iNextClose + 1)
                        }
                    }
                }
                --level
                println "close at: $iCloseLast, level: $level"
                if (level == 0) {
                    return "Curly Brackets OK!"
                } else if (level < 0) {
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