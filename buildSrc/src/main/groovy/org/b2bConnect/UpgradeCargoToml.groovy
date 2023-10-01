package org.b2bConnect

import groovy.toml.TomlBuilder
import groovy.toml.TomlSlurper

class UpgradeCargoToml {
    static void main(String[] args) {

        def path = new ProjectDirUriGet().ProjectDirUri()
        def fileToml = "${path}src-tauri/Cargo.toml"
        def targetDir = "${path}src-tauri"

        def inputFile = new File("$fileToml")
        def CargoDependencyTauri = new GetDependenciesCargo().CargoDependencies("tauri")
        def CargoDependencyTauriBuild = new GetDependenciesCargo().CargoDependencies("tauri-build")
        def slurped = new TomlSlurper().parseText(inputFile.text)

        def fg = 35
        def bg = 49
        def style = "${(char) 27}[$fg;$bg" + "m"

        if (slurped.dependencies.tauri.version == "$CargoDependencyTauri") {
            println(style + "cargo dependencies.tauri.version is up to date (\"$CargoDependencyTauri\")")
        } else {
            println(style + "cargo dependencies.tauri.version updated to: \"$CargoDependencyTauri\"")
        }
        if (slurped.'build-dependencies'.'tauri-build'.version == "$CargoDependencyTauriBuild") {
            println(style + "cargo build-dependencies.tauri-build.version is up to date (\"$CargoDependencyTauriBuild\")")
        } else {
            println(style + "cargo build-dependencies.tauri-build.version updated to: \"$CargoDependencyTauriBuild\"")
        }
        if (slurped.dependencies.tauri.features == ['shell-open']) {
            println(style + "cargo dependencies.tauri.features 'shell-open' removed")
        } else {
            println(style + "cargo dependencies.tauri.features is up to date ('shell-open' removed)")
        }
        if (slurped.lib.'crate-type' != ["staticlib", "cdylib", "rlib"]) {
            println(style + "cargo dependencies.lib.crate-type \"staticlib\", \"cdylib\", \"rlib\" are added")
        } else {
            println(style + "cargo dependencies.lib.crate-type is up to date (\"staticlib\", \"cdylib\", \"rlib\" already there)")
        }

        slurped.'build-dependencies'.'tauri-build'.version = "$CargoDependencyTauriBuild"
        slurped.dependencies.tauri.version = "$CargoDependencyTauri"
        slurped.dependencies.tauri.features = []
        slurped.lib = {
            'crate-type' "staticlib", "cdylib", "rlib"
        }


        def builder = new TomlBuilder()
        builder slurped
        new File("$targetDir/Cargo.toml").withWriter("UTF-8") { w -> builder.writeTo(w) }
        // Create a ProcessBuilder instance
        ProcessBuilder pb = new ProcessBuilder('cargo', 'update')

        // Set the current directory to /src-tauri
        pb.directory(new File(targetDir))

        // Start the process
        Process process = pb.start()

        // Wait for the process to finish
//        process.waitFor()
        process.waitForProcessOutput(System.out, System.err)

        // Check the exit code
        /*if (process.exitValue() != 0) {
            throw new ExecutionException("Cargo update failed with exit code ${process.exitValue()}")
        }*/
    }
}