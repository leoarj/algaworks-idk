# Build context - Utiliza a imagem do maven com mesma versão utilizada no projeto

# Para facilitar a definição da versão do Maven utilizada para build.
# Passar no docker-compose um valor diferente para o argumento caso mudar no projeto.
# Utilizando a imagem base do eclipse temurim com Java 21
ARG MAVEN_VERSION=3.9.11

FROM maven:${MAVEN_VERSION}-eclipse-temurin-21-alpine AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro e baixa dependências em cache
COPY pom.xml .

RUN mvn dependency:resolve dependency:resolve-plugins -DincludeScope=runtime -B

# Copia o restante do código
COPY src ./src

# Compila e empacota (gera o JAR na pasta target)
RUN mvn clean package -DskipTests

## STAGE RUN
# Use a imagem Eclipse Temurin JRE 21 como imagem base para a etapa de execução
FROM eclipse-temurin:21-alpine

# Define um argumento de build para o perfil de ambiente
ARG ENV=prod

# Define variáveis de ambiente para o nome do arquivo JAR, porta do servidor e perfil do Spring
ENV JAR_NAME=algaworks-idk-devcontainers-examples-meteorology-0.0.1-SNAPSHOT.jar \
    SERVER_PORT=8082 \
    SPRING_PROFILES_ACTIVE=$ENV \
    DOCKERIZE_VERSION=v0.9.3 \
    TZ=America/Sao_Paulo

# Comandos para OS Alpine
RUN addgroup -S spring && adduser -S -G spring spring && \
    apk add --no-cache tzdata curl && \
    curl -L https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
        | tar -xz -C /usr/local/bin && \
    cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && \
    apk del curl

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado na etapa de build para a etapa de execução
# Maven gera artefatos em target
COPY --from=build /app/target/$JAR_NAME .

# Copia o script de entrypoint
COPY --chown=spring:spring docker-entrypoint.sh ./

RUN chmod +x docker-entrypoint.sh

USER spring

# Adiciona um healthcheck para verificar se a aplicação está em execução
HEALTHCHECK --interval=15s --timeout=60s --start-period=10s --retries=3 \
    CMD curl -f http://localhost:$SERVER_PORT/actuator/health | grep -i UP || exit 1

# Expõe a porta do servidor para permitir acesso externo
EXPOSE $SERVER_PORT

# Usa o dockerize para aguardar o banco de dados antes de chamar o script de entrypoint (forma shell para permitir substituição de variáveis)
ENTRYPOINT ["/bin/sh", "-c", "dockerize -wait tcp://${DB_HOST:-localhost}:${DB_PORT:-5432} -timeout 60s ./docker-entrypoint.sh"]