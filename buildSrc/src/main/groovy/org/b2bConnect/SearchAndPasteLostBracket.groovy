package org.b2bConnect

class SearchAndPasteLostBracket {
    static void main(String[] args) {
        println searchLostBracketIndex("vite.config.ts")
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
        def level = 0

        if (iNextOpen < iNextClose) { // if "{" goes first in the file
            while (iNextOpen > 0) { // iterate until there is no more open bracket
                while (iNextOpen < iNextClose) { // iterate until the next "}" bracket is found
                    ++level // count how many open brackets go in a row
                    println "open bracket found at: $iNextOpen, level: $level, and iNextClose: $iNextClose"
                    iNextOpen = arrayToSearch.indexOf('{', iNextOpen + 1)
                    if (iNextOpen < 0) {
                        return iNextClose
                    }
                }
                iNextClose = arrayToSearch.indexOf('}', iNextClose + 1)
                --level
            }
        } else {
            return null // if "}" comes first in the file --> error
        }
    }
}