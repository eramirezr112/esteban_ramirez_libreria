@Library('threepoints-sharedlib') _

pipeline {
    agent any
    environment {
        // Definir una variable sonarenv
        sonarenv = 'http://localhost:9000'
    }
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
