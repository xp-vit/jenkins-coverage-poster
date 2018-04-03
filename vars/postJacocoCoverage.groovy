#!/usr/bin/env groovy

def call(Map args) {
    final Double coverageThreshold = args.threshold ?: 80.0
    final Double coverageDeltaThreshold = args.deltaThreshold ?: null
    final String xmlPath = args.xmlPath ?: "target/site/jacoco/jacoco.xml"

    final lib = new com.spotify.jenkinsfile.Coverage()

    final Double coverage = lib.getCoverageFromJacoco(xmlPath)
    println("Current coverage:" + coverage)
    lib.postCoverage(coverage, coverageThreshold)

    final Double coverageDelta = lib.getCoverageDelta()
    lib.postCoverageDelta(coverageDelta, coverageDeltaThreshold)
}
