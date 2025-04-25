#!/bin/bash

# Variáveis de ambiente não utilizadas aqui, a menos que usadas no docker-compose.yml
export HOST_PORT=8085
export PROJECT_ID=construtora

# Derruba os serviços definidos no docker-compose
docker-compose -f ./persistence-database/docker-compose-postgres-standalone.yml \
               --project-directory ./persistence-database/ \
               down
