package org.b2bConnect

class UpgradeViteConfigTs {
    static void main(String[] args) {

        def One = new PasteTextObject()

        One.elementOfLastOccurToSearch = "import "
        One.textToAdd = "import { internalIpV4 } from 'internal-ip'\n" as String
        One.fileToSearchName = "vite.config.ts"
        One.fileToPasteName = "vite.config.ts"
        One.startFromEndOfString = true
        One.startFromNewString = true

        PasteTextBySearch.main(One)
//----------------------------------------------------------------
        def Two = new PasteTextObject()

        Two.elementOfLastOccurToSearch = "export default defineConfig("
        Two.textToAdd = "async () => " as String
        Two.fileToSearchName = "vite.config.ts"
        Two.fileToPasteName = "vite.config.ts"
        Two.startFromEndOfString = false
        Two.startFromNewString = false

        PasteTextBySearch.main(Two)
//----------------------------------------------------------------
        def Three = new PasteTextObject()

        Three.elementOfLastOccurToSearch = "export default defineConfig(async () => {"
        Three.textToAdd = "  const host = await internalIpV4()\n\n  return {" as String
        Three.fileToSearchName = "vite.config.ts"
        Three.fileToPasteName = "vite.config.ts"
        Three.startFromEndOfString = true
        Three.startFromNewString = true

        PasteTextBySearch.main(Three)
//----------------------------------------------------------------
        def Four = new PasteTextObject()

        Four.elementOfLastOccurToSearch = "server: {"
        Four.textToAdd = "      host: '0.0.0.0', // listen on all addresses\n      hmr: {\n        protocol: 'ws',\n        host,\n        port: 1420,\n      }," as String
        Four.fileToSearchName = "vite.config.ts"
        Four.fileToPasteName = "vite.config.ts"
        Four.startFromEndOfString = true
        Four.startFromNewString = true

        PasteTextBySearch.main(Four)
//----------------------Closing bracket---------------------------
        def Five = new PasteTextObject()

        Five.elementOfLastOccurToSearch = ""
        Five.textToAdd = "}\n" as String
        Five.fileToSearchName = "vite.config.ts"
        Five.fileToPasteName = "vite.config.ts"
        Five.startFromEndOfString = false
        Five.startFromNewString = false

        if (SearchMissingBracket.searchLostBracketIndex("vite.config.ts")[0].toString().contains("Close curly brackets are missing:") ) {
            def indexWhereToPaste = new SearchMissingBracket().searchLostBracketIndex("vite.config.ts")[1]
            PasteTextByIndex.main(Five, indexWhereToPaste)
        }
    }
}