pipeline {
    agent any

    tools {
        maven 'Maven'
        
    }

    environment {
        IMAGE_NAME = 'library-management'
        CONTAINER_NAME = 'library-app'
        PORT = '8081'
    }

    stages {

        

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Image') {
            steps {
                bat 'podman build -t %IMAGE_NAME% .'
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                podman stop %CONTAINER_NAME% || exit 0
                podman rm %CONTAINER_NAME% || exit 0
                podman run -d -p %PORT%:8081 --name %CONTAINER_NAME% %IMAGE_NAME%
                '''
            }
        }
    }
}
