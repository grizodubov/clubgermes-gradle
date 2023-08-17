package org.b2bConnect

import static java.util.regex.Pattern.compile

class CargoDependenciesGet {
    static void main(String[] args) {
        println CargoDependencies("tauri")
    }

    static CargoDependencies(String tauriComponentName) {
        String tauriComponentOneGitVersionValue = getTauriComponentGitVersion tauriComponentName
        return tauriComponentOneGitVersionValue
    }


    static getTauriComponentGitVersion(String tauriComponentName) {
        String patternComponentVersion = /(\d*\.\d*\.\d*-alpha\.\d*)/
        String cmd = "cargo search $tauriComponentName --limit 1"
        def searchPackages = runProcess(cmd)
        def p = compile patternComponentVersion
        def m = p.matcher(searchPackages)
        m.find()
        return m.group(1)
    }

    static runProcess(cmd) {
        def path = new ProjectDirUriGet().ProjectDirUri()
        def workingDir = "${path}src-tauri"
        def process = cmd.execute(null, workingDir as File)
        def outputWriter = new StringWriter(), errorWriter = new StringWriter()
        process.waitForProcessOutput(outputWriter, errorWriter)
        String output = outputWriter.toString()
        String error = errorWriter.toString()
        int exitCode = process.exitValue()
        if (exitCode) {
            throw new Exception("Error: $error exit code: $exitCode")
        }
        return output
    }

}