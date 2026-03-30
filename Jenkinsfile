pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = 'library-management'
        CONTAINER_NAME = 'library-app'
        PORT = '8081'
        PODMAN = 'C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe'
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
                bat '"%PODMAN%" build -t %IMAGE_NAME% .'
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                "%PODMAN%" stop %CONTAINER_NAME% || echo not running
                "%PODMAN%" rm %CONTAINER_NAME% || echo not exist
                "%PODMAN%" run -d -p %PORT%:8081 --name %CONTAINER_NAME% %IMAGE_NAME%
                '''
            }
        }
    }
}
