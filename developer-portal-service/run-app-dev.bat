docker run -d --network kong-net -p27017:27017 --name mongo-devportal -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo

docker run --network kong-net -p 81:8091 %DOCKER_REPOSITORY_NAME%/developer-portal-service
