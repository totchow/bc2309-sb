# Remove all containers
docker rm $(docker ps -a -q)
# Remove all docker images
docker rmi $(docker images -q)

# recompile all jars and rebuild docker images
cd bc-crypto-coingecko
mvn clean install
docker build -t bc-crypto-coingecko:0.0.1 -f Dockerfile .
cd ..
cd bc-stock-finnhub
mvn clean install
docker build -t bc-stock-finnhub:0.0.1 -f Dockerfile .
cd ..
cd bc-product-data
mvn clean install
docker build -t bc-product-data:0.0.1 -f Dockerfile .
cd ..
cd bc-invest-simple
mvn clean install
docker build -t bc-invest-simple:0.0.1 -f Dockerfile .
cd ..
# Pull offical postgres and redis docker images
docker pull postgres
docker pull redis
# Run the containers by the docker images
docker run -d -p 8195:8090 bc-crypto-coingecko:0.0.1
docker run -d -p 8196:8091 bc-stock-finnhub:0.0.1
docker run -d -p 8197:8092 bc-product-data:0.0.1
docker run -d -p 8200:8100 bc-invest-simple:0.0.1