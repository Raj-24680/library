pipeline {
    agent any

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/Raj-24680/library-management.git'
            }
        }

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
                bat 'podman build -t library-management .'
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                podman stop library || exit 0
                podman rm library || exit 0
                podman run -d -p 8081:8081 --name library library-management
                '''
            }
        }
    }
}