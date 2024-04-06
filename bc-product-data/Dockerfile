FROM arm64v8/openjdk:17
LABEL maintainer="itsupport@gmail.com"
ADD target/bc-product-data-0.0.1-SNAPSHOT.jar bc-product-data-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","bc-product-data-0.0.1-SNAPSHOT.jar"]
EXPOSE 8092