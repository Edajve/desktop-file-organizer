pipeline {
    agent any  // This will run the pipeline on any available agent

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // This checks out the code from your source control
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'  // This will run Maven's clean and test commands
            }
        }
    }

    post {
        success {
            echo 'Build and Tests passed.'
        }
        failure {
            echo 'Build or Tests failed.'
        }
    }
}
