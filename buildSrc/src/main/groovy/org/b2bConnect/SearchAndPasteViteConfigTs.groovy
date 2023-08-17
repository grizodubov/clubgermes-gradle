package org.b2bConnect

class SearchAndPasteViteConfigTs {
    static void main(String[] args) {

        def SearchAndPasteTextObjectOne = new SearchAndPasteTextObject()

        SearchAndPasteTextObjectOne.elementOfLastOccurToSearch = "import "
        SearchAndPasteTextObjectOne.textToAdd = "\nimport { internalIpV4 } from 'internal-ip';\n" as String
        SearchAndPasteTextObjectOne.fileToSearchName = "vite.config.ts"
        SearchAndPasteTextObjectOne.fileToPasteName = "vite.config.ts"
        SearchAndPasteTextObjectOne.startFromEndOfString = true

        SearchAndPasteTextImport.main(SearchAndPasteTextObjectOne)
//----------------------------------------------------------------
        def SearchAndPasteTextObjectTwo = new SearchAndPasteTextObject()

        SearchAndPasteTextObjectTwo.elementOfLastOccurToSearch = "export default defineConfig("
        SearchAndPasteTextObjectTwo.textToAdd = "async () => " as String
        SearchAndPasteTextObjectTwo.fileToSearchName = "vite.config.ts"
        SearchAndPasteTextObjectTwo.fileToPasteName = "vite.config.ts"
        SearchAndPasteTextObjectTwo.startFromEndOfString = false

        SearchAndPasteTextImport.main(SearchAndPasteTextObjectTwo)
//----------------------------------------------------------------
        def SearchAndPasteTextObjectThree = new SearchAndPasteTextObject()

        SearchAndPasteTextObjectThree.elementOfLastOccurToSearch = "export default defineConfig(async () => {"
        SearchAndPasteTextObjectThree.textToAdd = "\n  const host = await internalIpV4()\n\n  return {" as String
        SearchAndPasteTextObjectThree.fileToSearchName = "vite.config.ts"
        SearchAndPasteTextObjectThree.fileToPasteName = "vite.config.ts"
        SearchAndPasteTextObjectThree.startFromEndOfString = true

        SearchAndPasteTextImport.main(SearchAndPasteTextObjectThree)
//----------------------------------------------------------------
        def SearchAndPasteTextObjectFour = new SearchAndPasteTextObject()

        SearchAndPasteTextObjectFour.elementOfLastOccurToSearch = "server: {"
        SearchAndPasteTextObjectFour.textToAdd = "\n      host: '0.0.0.0', // listen on all addresses\n      hmr: {\n        protocol: 'ws',\n        host,\n        port: 1420,\n      }," as String
        SearchAndPasteTextObjectFour.fileToSearchName = "vite.config.ts"
        SearchAndPasteTextObjectFour.fileToPasteName = "vite.config.ts"
        SearchAndPasteTextObjectFour.startFromEndOfString = true

        SearchAndPasteTextImport.main(SearchAndPasteTextObjectFour)
    }
}