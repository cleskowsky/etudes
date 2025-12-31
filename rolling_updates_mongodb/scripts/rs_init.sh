#!/usr/bin/env bash

echo "Initializing replica set..."
docker compose exec db mongosh -u admin -p admin --eval 'rs.initiate()'

echo "Adding second db server to replica set..."
docker compose exec db mongosh -u admin -p admin --eval 'rs.add("db2:27017")'

echo "Check replica set status..."
docker compose exec db mongosh -u admin -p admin --eval 'rs.status()'
