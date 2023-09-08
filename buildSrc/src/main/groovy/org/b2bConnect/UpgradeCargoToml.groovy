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
        pb.directory(new File (targetDir))

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