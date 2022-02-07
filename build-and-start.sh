#!/bin/sh
git pull
docker-compose build app-core
docker-compose up -d app-core
