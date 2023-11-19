node {
    def WORKSPACE = "/var/lib/jenkins/workspace/blog-service-deploy"
    def dockerImageTag = "blog-service:latest"
try{
    notifyBuild('STARTED')
    stage('Clone Repo') {
        // for display purposes
        // Get some code from a GitHub repository
        git url: 'https://github.com/adrianacastilloestepa/blog-service.git',
            credentialsId: 'f8879d2f-f6dd-49d7-a882-4b7af4bfc661',
            branch: 'master'
     }
    stage('Build docker') {
         dockerImage = docker.build("blog-service:latest")
    }
    stage('Deploy docker'){
          echo "Docker Image Tag Name: ${dockerImageTag}"
          sh "docker stop blog-service || true && docker rm blog-service || true"
          sh "docker network ls | grep adripoli || docker network create adripoli"
          sh "docker container run --network adripoli --name blog-service -d blog-service:latest -p 8090:8090"
    }
}catch(e){
    currentBuild.result = "FAILED"
    throw e
}finally{
    notifyBuild(currentBuild.result)
 }
}

def notifyBuild(String buildStatus = 'STARTED'){

  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()

  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "blog-service Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""

  // Email notification
  emailext (
     to: "adcastillo9@gmail.com",
     subject: subject_email,
     body: details,
     recipientProviders: [[$class: 'DevelopersRecipientProvider']]
  )

}