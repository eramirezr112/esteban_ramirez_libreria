def call(Map config) {    
    // Realiza el análisis estático de código
    try {

        // Accede a los parámetros del mapa usando config.<clave>
        boolean abortOnQualityGate = config.get('abortOnQualityGate', false) // Valor por defecto: false
        boolean abortPipeline = config.get('abortPipeline', false) // Valor por defecto: true        
        
        timeout(time: 5, unit: 'MINUTES') {
            // Supongamos que aquí se ejecuta el análisis estático, por ejemplo con SonarQube
            echo "Iniciando análisis estático de código..."
            // Ejecuta el análisis estático, reemplaza esto con el comando específico de tu análisis
            echo "Ejecución de las pruebas de calidad de código"

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
