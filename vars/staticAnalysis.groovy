def call(Map config) {
    // Simulación de las pruebas de calidad de código sin SonarQube
    try {
        // Accede a los parámetros del mapa usando config.<clave>
        boolean abortOnQualityGate = config.get('abortOnQualityGate', false) // Valor por defecto: false
        boolean abortPipeline = config.get('abortPipeline', false) // Valor por defecto: true    
        
        timeout(time: 5, unit: 'MINUTES') {
            // Manteniendo el sonarenv para asegurar compatibilidad futura si es necesario
            withSonarQubeEnv('SonarQube-Server') {
                echo "Iniciando pruebas de calidad de código..."
                
                // Simulación de ejecución de pruebas de calidad de código
                sh 'echo "Ejecución de las pruebas de calidad de código"'
                
                // Simulación de un resultado de QualityGate
                def qualityGateStatus = 'OK' // O cambiar a 'ERROR' si quieres probar el flujo de fallo
                echo "Resultado del QualityGate simulado: ${qualityGateStatus}"

                // Evalúa el resultado del QualityGate
                if (qualityGateStatus != 'OK' && abortOnQualityGate) {
                    error "El QualityGate no ha pasado. Abortando el pipeline."
                } else if (qualityGateStatus != 'OK') {
                    echo "El QualityGate no ha pasado, pero el pipeline continuará."
                } else {
                    echo "El QualityGate ha pasado exitosamente."
                }
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
