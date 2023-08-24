package org.b2bConnect

import groovy.transform.SourceURI

import java.nio.file.Path
import java.nio.file.Paths

class ProjectDirUriGet {
    static main(String[] args) {

    }

    static ProjectDirUri() {
        @SourceURI
        URI sourceUri

        def srcDirName = 'buildSrc'

        Path scriptLocation = Paths.get(sourceUri)
        def path = scriptLocation.toString().replaceAll("($srcDirName)[^&]*", "")

        return path

    }
}

