@Library('threepoints-sharedlib') _

pipeline {
    agent any
    environment {
        GIT_BRANCH = "${env.BRANCH_NAME}"  // Asegúrate de que la variable de entorno contenga el nombre de la rama
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
