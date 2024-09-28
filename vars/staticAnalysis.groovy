def call(Map config) {
    // Simulación de las pruebas de calidad de código sin SonarQube
    try {
        // Accede a los parámetros del mapa usando config.<clave>
        boolean abortOnQualityGate = config.get('abortOnQualityGate', false) // Valor por defecto: false
        boolean abortPipeline = config.get('abortPipeline', false) // Valor por defecto: true    
        String gitBranch = env.GIT_BRANCH
        
        timeout(time: 5, unit: 'MINUTES') {
            // Manteniendo el sonarenv para asegurar compatibilidad futura si es necesario
            
                echo "Iniciando pruebas de calidad de código..."
                
                // Simulación de ejecución de pruebas de calidad de código
                sh 'echo "Ejecución de las pruebas de calidad de código"'
                
                // Simulación de un resultado de QualityGate
                def qualityGateStatus = 'OK' // O cambiar a 'ERROR' si quieres probar el flujo de fallo
                echo "Resultado del QualityGate simulado: ${qualityGateStatus}"

                // Heurística para determinar si se debe cortar el pipeline según la rama
                if (abortOnQualityGate) {
                    echo "abortOnQualityGate es true, se cortará el pipeline si el QualityGate falla."
                    if (qualityGateStatus != 'OK') {
                        error "El QualityGate no ha pasado. Abortando el pipeline."
                    }
                } else {
                    echo "Evaluando nombre de la rama: ${gitBranch}"
                    
                    if (qualityGateStatus != 'OK') {
                        if (gitBranch == 'main') {
                            error "El QualityGate ha fallado en la rama master. Abortando el pipeline."
                        } else if (gitBranch.startsWith('hotfix')) {
                            error "El QualityGate ha fallado en una rama hotfix. Abortando el pipeline."
                        } else {
                            echo "El QualityGate ha fallado, pero no se abortará porque la rama no es master ni hotfix."
                        }
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
