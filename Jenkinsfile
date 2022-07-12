///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 TypeFox and others.
// 
// This program and the accompanying materials are made available under the
// terms of the Eclipse Public License 2.0 which is available at
// http://www.eclipse.org/legal/epl-2.0.
//
// SPDX-License-Identifier: EPL-2.0
//
///////////////////////////////////////////////////////////////////////////////

pipeline {
  agent any

  tools {
    maven 'M3'
  }

  triggers {
    // every fifteen minutes (perhaps at :07, :22, :37, :52)
    pollSCM """TZ=Europe/Berlin
    H/15 * * * *"""
  }

  options {
    // Keep at most 15 builds
    buildDiscarder logRotator(numToKeepStr: '15')
  }

  stages {
    stage('Clear m2 snapshots') {
      steps {
        sh 'rm -rf klighd-snapshots'
      }
    }

    stage('Build') {
      steps {
        echo "Using JDK at $JAVA_HOME"
        wrap([$class: 'Xvfb']) {
          sh "mvn clean deploy -B -fae" +
             " --define maven.repo.local=${env.WORKSPACE}/.repository" +
             " --define maven.test.failure.ignore=true"
        }
      }
    }

    stage('RevealArtifacts') {
      steps {
        sh 'rm -rf updatesite'
        sh 'mv build/de.cau.cs.kieler.klighd.repository/target/repository updatesite'
      }
    }
  }

  post {
    always {
      // Publish JUnit test result reports for them to show up in Jenkins
      junit 'test/**/surefire-reports/*.xml'
    }

    success {
      archiveArtifacts 'updatesite/**/*.*'
      archiveArtifacts 'klighd/**/*.*'
    }

    failure {
      // cs: wrapped the emailext call in a try catch block in order to prevent execution errors
      //  if the plugin 'email-ext' is unavailable; 'try-catch' blocks in turn are only available within 'script' blocks 
      script {
        try {
          emailext (
            subject: "KLighD build for branch ${env.BRANCH_NAME}: ${currentBuild.currentResult}'",
            body: """
                  <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
        } catch (Error e) {
          echo 'Sending eMail notifications failed.'
        }
      }
    }

    regression {
      // cs: wrapped the emailext call in a try catch block in order to prevent execution errors
      //  if the plugin 'email-ext' is unavailable; 'try-catch' blocks in turn are only available within 'script' blocks 
      script {
        try {
          emailext (
            subject: "KLighD build for branch ${env.BRANCH_NAME}: ${currentBuild.currentResult}'",
            body: """
                  <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
        } catch (Error e) {
          echo 'Sending eMail notifications failed.'
        }
      }
    }

    fixed {
      // cs: wrapped the emailext call in a try catch block in order to prevent execution errors
      //  if the plugin 'email-ext' is unavailable; 'try-catch' blocks in turn are only available within 'script' blocks 
      script {
        try {
          emailext (
            subject: "KLighD build for branch ${env.BRANCH_NAME} is back to normal'",
            body: """
                  <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
        } catch (Error e) {
          echo 'Sending eMail notifications failed.'
        }
      }
    }
  }
}
