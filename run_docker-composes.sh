#!/bin/bash

# Cria uma rede chamada 'lan', se ainda não existir
docker network create lan 2>/dev/null || echo "Rede 'lan' já existe"

# Sobe o serviço definido no docker-compose
docker-compose -f ./persistence-database/docker-compose-postgres-standalone.yml \
               --project-directory ./persistence-database/ \
               up -d
