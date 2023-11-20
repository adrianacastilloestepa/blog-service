node {
    def WORKSPACE = "/var/lib/jenkins/workspace/blog-service-deploy"
    def dockerImageTag = "blog-service:latest"
try{
    stage('Clone Repo') {
        // for display purposes
        // Get some code from a GitHub repository
        git url: 'https://github.com/adrianacastilloestepa/blog-service.git',
            credentialsId: 'f8879d2f-f6dd-49d7-a882-4b7af4bfc661',
            branch: 'master'
     }
    stage('Build docker') {
         dockerImage = docker.build("blog-service")
    }
    stage('Deploy docker'){
          echo "Docker Image Tag Name: ${dockerImageTag}"
          sh "docker stop blog-service || true && docker rm blog-service || true"
          sh "docker network ls | grep adripoli || docker network create adripoli"
          sh "docker container run --network adripoli -p 8090:8090 --name blog-service -d blog-service"
    }
}catch(e){
    currentBuild.result = "FAILED"
    throw e
}
}

