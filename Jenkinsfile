pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }
    stage('Deploy') {
      when {
        branch 'master'
      }
      steps {
        sh 'mvn deploy'
        sh "${env.JENKINS_SCRIPTS}/deploy-artifact.sh"
      }
    }
  }
}
