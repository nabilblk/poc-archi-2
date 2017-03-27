export DOCKER_IP=$(docker-machine ip poc-awb)
curl http://$DOCKER_IP:9000/api/clients
curl http://$DOCKER_IP:9000/api/products


* Config avec Git / File
    * Update-refresh / Autoreload.  
* Scale Discovery (Eureka): peer1:8765,peer2:8765,peer3:8765
* Spring Boot Admin : https://github.com/codecentric/spring-boot-admin  