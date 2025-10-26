#!/bin/sh
# docker-entrypoint.sh

set -e

# Configurações de JVM padrão
if [ -z "$JAVA_OPTS" ]; then
  JAVA_OPTS="-XX:MaxRAMPercentage=70.0 -Djava.security.egd=file:/dev/./urandom -Duser.timezone=America/Sao_Paulo"
fi

if [ "$DEBUG_MODE" = true ]; then
  echo "Modo de depuração ativado"
  # Java Debug Wire Protocol (JDWP)
  JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
fi

# Iniciar aplicação com as variáveis e argumentos configurados
echo "Iniciando AlgaTransito API com perfil: ${SPRING_PROFILES_ACTIVE:-default}"
echo "Porta configurada: ${SERVER_PORT:-8081}"
echo "Opções JVM: ${JAVA_OPTS}"

# Executar aplicação com configurações
exec java $JAVA_OPTS -jar ${JAR_NAME:-app.jar} $args