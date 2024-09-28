def call(boolean abortOnQualityGate = false, boolean abortPipeline = false) {
    // Realiza el análisis estático de código
    try {
        timeout(time: 5, unit: 'MINUTES') {
            // Supongamos que aquí se ejecuta el análisis estático, por ejemplo con SonarQube
            echo "Iniciando análisis estático de código..."
            // Ejecuta el análisis estático, reemplaza esto con el comando específico de tu análisis
            sh 'Ejecución de las pruebas de calidad de código'

            // Espera el resultado del QualityGate
            def qualityGate = waitForQualityGate()
            echo "Resultado del QualityGate: ${qualityGate.status}"

            // Evalúa el resultado del QualityGate
            if (qualityGate.status != 'OK' && abortOnQualityGate) {
                error "El QualityGate no ha pasado. Abortando el pipeline."
            } else if (qualityGate.status != 'OK') {
                echo "El QualityGate no ha pasado, pero el pipeline continuará."
            } else {
                echo "El QualityGate ha pasado exitosamente."
            }
        }

        // Verificar el parámetro abortPipeline
        if (abortPipeline) {
            error "Abortando el pipeline según el parámetro abortPipeline."
        } else {
            echo "El pipeline continuará según el parámetro abortPipeline."
        }

    } catch (Exception e) {
        error "Error durante el análisis estático: ${e.message}"
    }
}
