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
         stage('Start Podman') {
    steps {
        bat '''
        "C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe" machine init || exit 0
        "C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe" machine start
        '''
    }
}

        stage('Build Image') {
            steps {
                bat '"C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe" build -t %IMAGE_NAME% .'
    }
}
  

    stage('Deploy') {
        steps {
            bat '''
            "C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe" stop %CONTAINER_NAME% || exit 0
            "C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe" rm %CONTAINER_NAME% || exit 0
            "C:\\Users\\rahul\\AppData\\Local\\Programs\\Podman\\podman.exe" run -d -p %PORT%:8081 --name %CONTAINER_NAME% %IMAGE_NAME%
            '''
    }
}
    }
}
