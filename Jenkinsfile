@Library('threepoints-sharedlib') _

pipeline {
    agent any
    stages {
        stage('Análisis estático') {
            steps {
                script {
                    // Ejecuta el análisis estático con ambos parámetros
                    staticAnalysis(abortOnQualityGate: true, abortPipeline: false)
                }
            }
        }
    }
}
