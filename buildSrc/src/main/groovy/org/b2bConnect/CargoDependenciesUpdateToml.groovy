package org.b2bConnect

import groovy.toml.TomlBuilder
import groovy.toml.TomlSlurper

class CargoDependenciesUpdateToml {
    static void main(String[] args) {

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileToml = "${path}src-tauri/Cargo.toml"
        def targetDir = "${path}src-tauri"

        def inputFile = new File("$fileToml")
        def CargoDependencyTauri = new CargoDependenciesGet().CargoDependencies("tauri")
        def CargoDependencyTauriBuild = new CargoDependenciesGet().CargoDependencies("tauri")
        def slurped = new TomlSlurper().parseText(inputFile.text)
        slurped.'build-dependencies'.'tauri-build'.version = "$CargoDependencyTauriBuild"
        slurped.dependencies.tauri.version = "$CargoDependencyTauri"
        slurped.dependencies.tauri.features = []
        slurped.lib = {
            'crate-type' "staticlib", "cdylib", "rlib"
        }
        def builder = new TomlBuilder()
        builder slurped
        new File("$fileToml").withWriter("UTF-8") { w -> builder.writeTo(w) }
    }
}